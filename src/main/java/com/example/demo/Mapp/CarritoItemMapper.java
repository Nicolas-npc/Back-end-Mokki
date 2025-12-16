package com.example.demo.Mapp;

import org.springframework.stereotype.Component;
import com.example.demo.Model.CarritoItemModel;
import com.example.demo.Dto.Respuesta.CarritoitemsResponsDTO;

@Component
public class CarritoItemMapper {

    public CarritoitemsResponsDTO toResponse(CarritoItemModel model) {
        if (model == null) {
            return null;
        }
        
        CarritoitemsResponsDTO response = new CarritoitemsResponsDTO();
        response.setId(model.getId());
        response.setNombre(model.getProducto().getNombre());
        response.setPrecio(model.getPrecioUnitario());
        response.setCantidad(model.getCantidad());
        response.setTotal(model.getPrecioUnitario() * model.getCantidad());
        
        return response;
    }
}
