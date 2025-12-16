package com.example.demo.Mapp;

import org.springframework.stereotype.Component;
import com.example.demo.Model.ProductoModel;
import com.example.demo.Dto.Request.ProductosRequestDTO;
import com.example.demo.Dto.Respuesta.ProductoResponse;

@Component
public class ProductoMapper {

    public ProductoModel toModel(ProductosRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ProductoModel producto = new ProductoModel();
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getStock());
        producto.setDescripcion(dto.getDescripcion());
        
        return producto;
    }

    public ProductoResponse toResponse(ProductoModel model) {
        if (model == null) {
            return null;
        }
        
        ProductoResponse response = new ProductoResponse();
        response.setId(Long.valueOf(model.getId()));
        response.setNombre(model.getNombre());
        response.setCategoria(model.getCategoria());
        response.setPrecio(model.getPrecio());
        response.setStock(model.getCantidad());
        response.setDescripcion(model.getDescripcion());
        
        return response;
    }

    public void updateModelFromDto(ProductosRequestDTO dto, ProductoModel model) {
        if (dto == null || model == null) {
            return;
        }
        
        if (dto.getNombre() != null) {
            model.setNombre(dto.getNombre());
        }
        if (dto.getCategoria() != null) {
            model.setCategoria(dto.getCategoria());
        }
        if (dto.getPrecio() != null) {
            model.setPrecio(dto.getPrecio());
        }
        if (dto.getStock() != null) {
            model.setCantidad(dto.getStock());
        }
        if (dto.getDescripcion() != null) {
            model.setDescripcion(dto.getDescripcion());
        }
    }
}
