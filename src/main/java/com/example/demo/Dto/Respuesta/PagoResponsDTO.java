package com.example.demo.Dto.Respuesta;

import java.time.LocalDateTime;

import com.example.demo.Model.PagoModel;

import lombok.Data;

@Data
public class PagoResponsDTO {
    private int id;
    private int idPedido;
    private Double monto;
    private LocalDateTime fechaPago;
    private PagoModel.MetodoPago metodoPago;
    private String numeroTransaccion;
}
