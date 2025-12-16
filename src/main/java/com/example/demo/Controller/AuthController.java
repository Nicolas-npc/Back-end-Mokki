package com.example.demo.Controller;

import com.example.demo.Dto.Request.LoginRequestDTO;
import com.example.demo.Dto.Request.RegistroRequesDTO;
import com.example.demo.Dto.Respuesta.LoginResponseDTO;
import com.example.demo.Dto.Respuesta.RolResponsDTO;
import com.example.demo.Dto.Respuesta.UserResponseDTO;
import com.example.demo.Model.RolModel;
import com.example.demo.Model.UserModel;
import com.example.demo.Repository.RolRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,RolRepository rolRepository,JwtService jwtService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public ResponseEntity<LoginResponseDTO> registro(@Valid @RequestBody RegistroRequesDTO dto) {
        RolModel rol = rolRepository.findById(dto.getRolId().intValue())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UserModel nuevo = UserModel.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .contrasena(passwordEncoder.encode(dto.getContrasena()))
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .rol(rol)
                .build();

        UserModel guardado = userRepository.save(nuevo);

        String token = jwtService.generarToken(
                guardado.getEmail(),
                Map.of("rol", guardado.getRol().getNombre(), "id", guardado.getId())
        );

        LoginResponseDTO resp = construirLoginResponse(guardado, token, "Registro exitoso");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        UserModel user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(dto.getContrasena(), user.getContrasena())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtService.generarToken(
                user.getEmail(),
                Map.of("rol", user.getRol().getNombre(), "id", user.getId())
        );

        LoginResponseDTO resp = construirLoginResponse(user, token, "Login exitoso");
        return ResponseEntity.ok(resp);
    }

    private LoginResponseDTO construirLoginResponse(UserModel user, String token, String mensaje) {
        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setId((long) user.getId());
        userDto.setNombre(user.getNombre());
        userDto.setEmail(user.getEmail());
        userDto.setTelefono(user.getTelefono());
        userDto.setDireccion(user.getDireccion());

        RolResponsDTO rolDto = new RolResponsDTO();
        rolDto.setId((long) user.getRol().getId());
        rolDto.setNombre(user.getRol().getNombre());
        rolDto.setDescripcion(user.getRol().getDescripcion());
        userDto.setRol(rolDto);

        return new LoginResponseDTO(true, mensaje, token, (long) user.getId(), userDto);
    }
}
