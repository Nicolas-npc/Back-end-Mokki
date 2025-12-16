package com.example.demo.Mapp;

import org.springframework.stereotype.Component;
import com.example.demo.Model.RolModel;
import com.example.demo.Dto.Respuesta.RolResponsDTO;

@Component
public class RolMapper {

    public RolResponsDTO toResponse(RolModel model) {
        if (model == null) {
            return null;
        }
        
        RolResponsDTO response = new RolResponsDTO();
        response.setId(Long.valueOf(model.getId()));
        response.setNombre(model.getNombre());
        
        return response;
    }
}
