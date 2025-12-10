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
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
    private double precioTotal;
    private String estado;
    private String fechaPedido;
    private String fechaEntrega;
    private String metodoPago;
    private String direccionEnvio;
    private String nombreCliente;
    private String telefonoCliente;
}
