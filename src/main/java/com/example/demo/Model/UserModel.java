package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    private String nombre;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String contrasena;
    private String telefono;
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolModel rol;
}
