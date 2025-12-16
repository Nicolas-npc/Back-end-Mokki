package com.example.demo.Dto.Respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemResponsDTO {
    private int id;
    private int productoId;
    private String productoNombre;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
}
