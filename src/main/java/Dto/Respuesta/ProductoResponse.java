package Dto.Respuesta;

import lombok.Data;

@Data
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
    private String descripcion;
}
