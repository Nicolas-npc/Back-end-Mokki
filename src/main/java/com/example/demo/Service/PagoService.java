package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.PagoModel;
import com.example.demo.Repository.PagoRepository;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;
            
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public PagoModel crearPago(PagoModel pago) {
        return pagoRepository.save(pago);
    }

    public List<PagoModel> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public PagoModel actualizarPago(int id, PagoModel nuevo) {
        PagoModel existente = pagoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setId(nuevo.getId());
            existente.setMetodoPago(nuevo.getMetodoPago());
            existente.setMonto(nuevo.getMonto());
            return pagoRepository.save(existente);
        }
        return null;
    }
    
    public void eliminarPago(int id) {
        pagoRepository.deleteById(id);
    }

    public PagoModel buscarPorId(int id) {
        return pagoRepository.findById(id).orElse(null);
    }
}
