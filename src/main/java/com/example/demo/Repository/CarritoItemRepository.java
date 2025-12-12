package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.CarritoItemModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItemModel, Integer> {
    List<CarritoItemModel> findByCarritoId(int carritoId);
    Optional<CarritoItemModel> findByCarritoIdAndCoffeeId(int carritoId, int coffeeId);
    void deleteByCarritoId(int carritoId);
}
