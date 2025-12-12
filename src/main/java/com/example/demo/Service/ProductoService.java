package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.Model.ProductoModel;
import com.example.demo.Repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoModel crearProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public List<ProductoModel> obtenerTodos() {
        return productoRepository.findAll();
    }

    public ProductoModel actualizarProducto(int id, ProductoModel nuevo) {
        ProductoModel existente = productoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setNombre(nuevo.getNombre());
            existente.setDescripcion(nuevo.getDescripcion());
            existente.setPrecio(nuevo.getPrecio());
            existente.setCantidad(nuevo.getCantidad());
            return productoRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(int id) {
        productoRepository.deleteById(id);
    }

    public ProductoModel buscarPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }
}
