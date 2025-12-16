package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.*;
import com.example.demo.Model.CarritoItemModel;
import com.example.demo.Model.CarritoModel;
import com.example.demo.Model.CarritoModel.EstadoCarrito;
import com.example.demo.Repository.CarritoItemRepository;
import com.example.demo.Repository.CarritoRepository;
import com.example.demo.Repository.ProductoRepository;
import jakarta.transaction.Transactional;


@Service
public class CarritoService {
 @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoItemRepository carritoItemRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    public CarritoModel obtenerCarritoActivo(UserModel usuario) {
        Optional<CarritoModel> carritoOpt = carritoRepository.findByUsuarioIdAndEstado(
            usuario.getId(), EstadoCarrito.EN_USO);
        
        if (carritoOpt.isPresent()) {
            return carritoOpt.get();
        }
        
        CarritoModel nuevoCarrito = CarritoModel.builder()
            .user(usuario)
            .fechaGenerado(LocalDateTime.now())
            .estado(EstadoCarrito.EN_USO)
            .build();
        
        return carritoRepository.save(nuevoCarrito);
    }

    @Transactional
    public CarritoModel agregarItem(int carritoId, int coffeeId, int cantidad) {
        CarritoModel carrito = carritoRepository.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        
        ProductoModel producto = productoRepository.findById(coffeeId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (producto.getCantidad() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        
        Optional<CarritoItemModel> itemExistente = carritoItemRepository
            .findByCarritoIdAndCoffeeId(carritoId, coffeeId);
        
        if (itemExistente.isPresent()) {
            CarritoItemModel item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            carritoItemRepository.save(item);
        } else {
            CarritoItemModel nuevoItem = new CarritoItemModel();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(cantidad);
            nuevoItem.setPrecioUnitario(producto.getPrecio());
            carritoItemRepository.save(nuevoItem);
        }
        
        return carritoRepository.findById(carritoId).orElse(carrito);
    }

    @Transactional
    public CarritoModel actualizarCantidadItem(int itemId, int nuevaCantidad) {
        CarritoItemModel item = carritoItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        if (nuevaCantidad <= 0) {
            carritoItemRepository.delete(item);
        } else {
            if (item.getProducto().getCantidad() < nuevaCantidad) {
                throw new RuntimeException("Stock insuficiente");
            }
            item.setCantidad(nuevaCantidad);
            carritoItemRepository.save(item);
        }
        
        return carritoRepository.findById(item.getCarrito().getId())
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    @Transactional
    public void eliminarItem(int itemId) {
        carritoItemRepository.deleteById(itemId);
    }

    @Transactional
    public void vaciarCarrito(int carritoId) {
        carritoItemRepository.deleteByCarritoId(carritoId);
    }

    public CarritoModel cambiarEstado(int carritoId, EstadoCarrito nuevoEstado) {
        CarritoModel carrito = carritoRepository.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.setEstado(nuevoEstado);
        return carritoRepository.save(carrito);
    }

    public List<CarritoModel> obtenerCarritosPorUsuario(int usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public CarritoModel buscarPorId(int id) {
        return carritoRepository.findById(id).orElse(null);
    }
}
