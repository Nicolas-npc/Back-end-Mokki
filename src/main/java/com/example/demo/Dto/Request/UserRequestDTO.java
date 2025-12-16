package com.example.demo.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank@Email
    private String email;

    @NotBlank
    private String contrasena;

    private String telefono;
    private String direccion;

    @NotNull
    private Long rolId;
}
