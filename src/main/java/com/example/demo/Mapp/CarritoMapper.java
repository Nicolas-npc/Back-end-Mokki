package com.example.demo.Mapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.Model.CarritoModel;
import com.example.demo.Dto.Respuesta.CarritoResponsDTO;
import com.example.demo.Dto.Respuesta.CarritoitemsResponsDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {

    @Autowired
    private CarritoItemMapper carritoItemMapper;

    public CarritoResponsDTO toResponse(CarritoModel model) {
        if (model == null) {
            return null;
        }
        
        CarritoResponsDTO response = new CarritoResponsDTO();
        response.setIdCarrito(model.getId());
        response.setIdUsuario(model.getUser().getId());
        response.setNombreUsuario(model.getUser().getNombre());
        response.setFechaCreacion(model.getFechaGenerado());
        response.setEstado(model.getEstado());
        response.setTotalCarrito(model.calcularTotal());
        
        if (model.getItems() != null) {
            List<CarritoitemsResponsDTO> itemsResponse = model.getItems().stream()
                .map(carritoItemMapper::toResponse)
                .collect(Collectors.toList());
            response.setItems(itemsResponse);
        } else {
            response.setItems(new ArrayList<>());
        }
        
        return response;
    }
}
