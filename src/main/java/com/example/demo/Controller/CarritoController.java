package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.Dto.Request.CarritoItemRequestDTO;
import com.example.demo.Model.CarritoModel;
import com.example.demo.Model.UserModel;
import com.example.demo.Service.CarritoService;
import com.example.demo.Service.UserService;
@RestController
@RequestMapping("/carrito")
public class CarritoController {
    
    private final CarritoService carritoService;
    private final UserService userService;

    public CarritoController(CarritoService carritoService, UserService userService) {
        this.carritoService = carritoService;
        this.userService = userService;
    }

    @GetMapping("/activo/{usuarioId}")
    public ResponseEntity<CarritoModel> obtenerCarritoActivo(@PathVariable int usuarioId) {
        UserModel usuario = userService.buscarPorId(usuarioId);
        CarritoModel carrito = carritoService.obtenerCarritoActivo(usuario);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/items")
    public ResponseEntity<CarritoModel> agregarItem(@RequestBody CarritoItemRequestDTO dto) {
        CarritoModel carrito = carritoService.agregarItem(dto.getCarritoId(), dto.getProductoId(), dto.getCantidad());
        return ResponseEntity.ok(carrito);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CarritoModel> actualizarCantidadItem(@PathVariable int itemId,@RequestParam int cantidad) {
        CarritoModel carrito = carritoService.actualizarCantidadItem(itemId, cantidad);
        return ResponseEntity.ok(carrito);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable int itemId) {
        carritoService.eliminarItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{carritoId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable int carritoId) {
        carritoService.vaciarCarrito(carritoId);
        return ResponseEntity.noContent().build();
    }
}