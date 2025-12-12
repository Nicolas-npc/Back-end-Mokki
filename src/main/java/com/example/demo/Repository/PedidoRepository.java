package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.PedidoModel;  
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
    List<PedidoModel> findByUsuarioId(int usuarioId);
    List<PedidoModel> findByEstado(PedidoModel.EstadoPedido estado);
}
