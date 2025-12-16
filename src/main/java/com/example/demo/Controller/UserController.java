package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Dto.Request.UserRequestDTO;
import com.example.demo.Dto.Respuesta.UserResponseDTO;
import com.example.demo.Dto.Respuesta.RolResponsDTO;
import com.example.demo.Model.RolModel;
import com.example.demo.Model.UserModel;
import com.example.demo.Repository.RolRepository;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RolRepository rolRepository;

    public UserController(UserService userService, RolRepository rolRepository) {
        this.userService = userService;
        this.rolRepository = rolRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listaUser() {
        List<UserResponseDTO> users = userService.obtenerTodos()
            .stream()
            .map(this::toUserResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> obtenerUser(@PathVariable int id) {
        UserModel user = userService.buscarPorId(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toUserResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> crearUser(@Valid @RequestBody UserRequestDTO dto) {
        UserModel nuevo = toUserModel(dto, null);
        UserModel creado = userService.crearUsuario(nuevo);
        return ResponseEntity
            .created(URI.create("/users/" + creado.getId()))
            .body(toUserResponseDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> actualizarUser(@PathVariable int id, @Valid @RequestBody UserRequestDTO dto) {
        UserModel modelo = toUserModel(dto, id);
        UserModel actualizado = userService.actualizarUsuario(id, modelo);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toUserResponseDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUser(@PathVariable int id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    private UserModel toUserModel(UserRequestDTO dto, Integer id) {
        RolModel rol = rolRepository.findById(dto.getRolId().intValue())
            .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + dto.getRolId()));

        UserModel user = new UserModel();
        if (id != null) {
            user.setId(id);
        }
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setContrasena(dto.getContrasena());
        user.setTelefono(dto.getTelefono());
        user.setDireccion(dto.getDireccion());
        user.setRol(rol);
        return user;
    }

    private UserResponseDTO toUserResponseDTO(UserModel user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId((long) user.getId());
        dto.setNombre(user.getNombre());
        dto.setEmail(user.getEmail());
        dto.setTelefono(user.getTelefono());
        dto.setDireccion(user.getDireccion());

        RolResponsDTO rolDto = new RolResponsDTO();
        rolDto.setId((long) user.getRol().getId());
        rolDto.setNombre(user.getRol().getNombre());
        rolDto.setDescripcion(user.getRol().getDescripcion());
        dto.setRol(rolDto);

        return dto;
    }

}
