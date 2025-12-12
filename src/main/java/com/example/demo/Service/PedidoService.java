package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.PedidoModel;
import com.example.demo.Repository.PedidoRepository;

@Service
public class PedidoService {
        @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoModel crearPedido(PedidoModel pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel actualizarPedido(int id, PedidoModel nuevo) {
        PedidoModel existente = pedidoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setUser(nuevo.getUser());
            existente.setCarrito(null);
            existente.setTotal(nuevo.getTotal());
            existente.setFechaPedido(nuevo.getFechaPedido());           
            existente.setEstado(nuevo.getEstado());
            return pedidoRepository.save(existente);
        }
        return null;
    }

    public void eliminarPedido(int id) {
        pedidoRepository.deleteById(id);
    }

    public PedidoModel buscarPorId(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
