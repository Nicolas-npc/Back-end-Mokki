package Dto.Respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoitemsResponsDTO {
    private int id;
    private String nombre;
    private Double precio;
    private int cantidad;
    private Double total;
}
