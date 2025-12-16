package com.example.demo.Dto.Request;

import com.example.demo.Model.PagoModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class PagoRequestDTO {
    @NotNull
    private Long pedidoId;

    @NotNull
    @Positive
    private Double monto;

    @NotNull
    private PagoModel.MetodoPago metodoPago;
}
