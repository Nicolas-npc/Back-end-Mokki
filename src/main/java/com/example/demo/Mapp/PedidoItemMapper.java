package com.example.demo.Mapp;

import org.springframework.stereotype.Component;
import com.example.demo.Model.PedidoItemModel;
import com.example.demo.Dto.Respuesta.PedidoItemResponsDTO;

@Component
public class PedidoItemMapper {

    public PedidoItemResponsDTO toResponse(PedidoItemModel model) {
        if (model == null) {
            return null;
        }
        
        PedidoItemResponsDTO response = new PedidoItemResponsDTO();
        response.setId(model.getId());
        response.setProductoId(model.getProducto().getId());
        response.setProductoNombre(model.getProducto().getNombre());
        response.setCantidad(model.getCantidad());
        response.setPrecioUnitario(model.getPrecioUnitario());
        response.setSubtotal(model.getCantidad() * model.getPrecioUnitario());
        
        return response;
    }
}
