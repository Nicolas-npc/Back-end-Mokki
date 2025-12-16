package com.example.demo.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.Model.PedidoModel;
import com.example.demo.Service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> listarPedidos(){
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> obtenerPedido(@PathVariable int id){
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoModel> crearPedido(@RequestBody PedidoModel pedido){
        PedidoModel creado = pedidoService.crearPedido(pedido);
        return ResponseEntity
                .created(URI.create("/pedidos/" + creado.getId()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoModel> actualizarPedido(@PathVariable int id, @RequestBody PedidoModel pedido){
        PedidoModel actualizado = pedidoService.actualizarPedido(id, pedido);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable int id){
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
