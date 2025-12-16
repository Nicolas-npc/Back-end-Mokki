package com.example.demo.Mapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.Model.UserModel;
import com.example.demo.Model.RolModel;
import com.example.demo.Dto.Request.UserRequestDTO;
import com.example.demo.Dto.Respuesta.UserResponseDTO;

@Component
public class UserMapper {

    @Autowired
    private RolMapper rolMapper;

    public UserModel toModel(UserRequestDTO dto, RolModel rol) {
        if (dto == null) {
            return null;
        }
        
        UserModel user = new UserModel();
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setContrasena(dto.getContrasena());
        user.setTelefono(dto.getTelefono());
        user.setDireccion(dto.getDireccion());
        user.setRol(rol);
        
        return user;
    }

    public UserResponseDTO toResponse(UserModel model) {
        if (model == null) {
            return null;
        }
        
        UserResponseDTO response = new UserResponseDTO();
        response.setId(Long.valueOf(model.getId()));
        response.setNombre(model.getNombre());
        response.setEmail(model.getEmail());
        response.setTelefono(model.getTelefono());
        response.setDireccion(model.getDireccion());
        
        if (model.getRol() != null) {
            response.setRol(rolMapper.toResponse(model.getRol()));
        }
        
        return response;
    }

    public void updateModelFromDto(UserRequestDTO dto, UserModel model, RolModel rol) {
        if (dto == null || model == null) {
            return;
        }
        
        if (dto.getNombre() != null) {
            model.setNombre(dto.getNombre());
        }
        if (dto.getEmail() != null) {
            model.setEmail(dto.getEmail());
        }
        if (dto.getContrasena() != null) {
            model.setContrasena(dto.getContrasena());
        }
        if (dto.getTelefono() != null) {
            model.setTelefono(dto.getTelefono());
        }
        if (dto.getDireccion() != null) {
            model.setDireccion(dto.getDireccion());
        }
        if (rol != null) {
            model.setRol(rol);
        }
    }
}
