package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.PedidoItemModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemModel, Integer> {

    List<PedidoItemModel> findByPedidoId(int pedidoId);

    List<PedidoItemModel> findByProductoId(int productoId);

    Optional<PedidoItemModel> findByPedidoIdAndProductoId(int pedidoId, int productoId);
}
