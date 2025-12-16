package com.example.demo.Mapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.Model.PedidoModel;
import com.example.demo.Dto.Respuesta.PedidoResponsDTO;

@Component
public class PedidoMapper {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private CarritoMapper carritoMapper;

    public PedidoResponsDTO toResponse(PedidoModel model) {
        if (model == null) {
            return null;
        }
        
        PedidoResponsDTO response = new PedidoResponsDTO();
        response.setId(Long.valueOf(model.getId()));
        response.setTotal(Double.parseDouble(model.getTotal()));
        response.setFechaPedido(model.getFechaPedido().toLocalDate());
        
        if (model.getUser() != null) {
            response.setUsuario(userMapper.toResponse(model.getUser()));
        }
        
        if (model.getCarrito() != null) {
            response.setCarrito(carritoMapper.toResponse(model.getCarrito()));
        }
        
        return response;
    }
}
