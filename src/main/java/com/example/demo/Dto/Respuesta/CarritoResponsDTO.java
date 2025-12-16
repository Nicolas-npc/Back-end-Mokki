package com.example.demo.Dto.Respuesta;

import java.util.List;
import java.time.LocalDateTime;
import com.example.demo.Model.CarritoModel.EstadoCarrito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponsDTO {
    private int idCarrito;
    private int idUsuario;
    private String nombreUsuario;
    private List <CarritoitemsResponsDTO> items;
    private LocalDateTime fechaCreacion;
    private EstadoCarrito estado;
    private Double totalCarrito;
}
