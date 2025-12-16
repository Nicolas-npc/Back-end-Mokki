package com.example.demo.Dto.Respuesta;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PedidoResponsDTO {
    private Long id;
    private UserResponseDTO usuario;
    private CarritoResponsDTO carrito;
    private double total;
    private LocalDate fechaPedido;
}
    