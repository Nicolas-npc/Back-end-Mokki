package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.CarritoModel;
import com.example.demo.Model.CarritoModel.EstadoCarrito;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoModel, Integer> {
    Optional<CarritoModel> findByUsuarioIdAndEstado(int usuarioId, EstadoCarrito estado);
    List<CarritoModel> findByUsuarioId(int usuarioId);
    List<CarritoModel> findByEstado(EstadoCarrito estado);    
}
