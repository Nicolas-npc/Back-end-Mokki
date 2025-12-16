package Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductosRequestDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String categoria;

    @NotBlank
    @Positive
    private Double precio;

    @NotBlank
    @Positive
    private Integer stock;

    private String descripcion;
}


