package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "carrito_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private CarritoModel carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoModel producto;

    private int cantidad;
    private double precioUnitario;

    // Add this setter method
    public void setCarrito(CarritoModel carrito) {
        this.carrito = carrito;
    }
}
