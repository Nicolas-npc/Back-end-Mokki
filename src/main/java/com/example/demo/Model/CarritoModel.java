package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name = "carrito")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  
    private UserModel user;

    @OneToMany(mappedBy = "carritoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<CarritoItemModel> items;

    private LocalDateTime fechaGenerado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCarrito estado;

    public enum EstadoCarrito {
        EN_USO,
        COMPLETADO,
        CONFIRMADO 
    }

    public double calcularTotal() {
        return items != null ? items.stream()
            .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
            .sum() : 0.0;
    }
}
