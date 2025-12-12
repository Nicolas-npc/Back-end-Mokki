package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.PagoModel;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Integer> {
    List<PagoModel> findByPedidoId(int pedidoId);
    List<PagoModel> findByMetodoPago(PagoModel.MetodoPago metodoPago);
}
    