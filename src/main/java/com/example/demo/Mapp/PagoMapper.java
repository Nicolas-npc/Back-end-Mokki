package com.example.demo.Mapp;

import org.springframework.stereotype.Component;
import com.example.demo.Model.PagoModel;
import com.example.demo.Dto.Respuesta.PagoResponsDTO;

@Component
public class PagoMapper {

    public PagoResponsDTO toResponse(PagoModel model) {
        if (model == null) {
            return null;
        }
        
        PagoResponsDTO response = new PagoResponsDTO();
        response.setId(model.getId());
        response.setIdPedido(model.getPedido().getId());
        response.setMonto(model.getMonto());
        response.setMetodoPago(model.getMetodoPago());
        response.setFechaPago(model.getFechaPago());
        response.setNumeroTransaccion(null); // Si tienes este campo en el modelo, agrégalo
        
        return response;
    }
}
