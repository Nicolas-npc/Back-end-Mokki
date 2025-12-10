package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pedido_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
    private double precioTotal;
}
