package Dto.Respuesta;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private RolResponsDTO rol;
}
