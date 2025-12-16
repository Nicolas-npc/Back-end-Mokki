package com.example.demo.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser un correo electrónico válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;
}
