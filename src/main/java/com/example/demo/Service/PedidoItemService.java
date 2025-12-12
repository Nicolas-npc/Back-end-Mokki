package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.PedidoItemModel;
import com.example.demo.Repository.PedidoItemRepository;

@Service
public class PedidoItemService {
    @Autowired
    private PedidoItemRepository pedidoItemRepository;
            
    public PedidoItemService(PedidoItemRepository pedidoItemRepository) {
        this.pedidoItemRepository = pedidoItemRepository;
    }

    public PedidoItemModel crearPedidoItem(PedidoItemModel pedidoItem) {
        return pedidoItemRepository.save(pedidoItem);
    }

    public List<PedidoItemModel> obtenerTodos() {
        return pedidoItemRepository.findAll();
    }

    public PedidoItemModel actualizarPedidoItem(int id, PedidoItemModel nuevo) {
        PedidoItemModel existente = pedidoItemRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setPedido(nuevo.getPedido());
            existente.setProducto(nuevo.getProducto());
            existente.setCantidad(nuevo.getCantidad());
            existente.setPrecioUnitario(nuevo.getPrecioUnitario());
            existente.setPrecioTotal(nuevo.getPrecioTotal());
            return pedidoItemRepository.save(existente);
        }
        return null;
    }
    
    public void eliminarPedidoItem(int id) {
        pedidoItemRepository.deleteById(id);
    }

    public PedidoItemModel buscarPorId(int id) {
        return pedidoItemRepository.findById(id).orElse(null);
    }
}
