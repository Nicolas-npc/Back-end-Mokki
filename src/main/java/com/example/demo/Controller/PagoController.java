package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.Model.PagoModel;
import com.example.demo.Service.PagoService;


@RestController
@RequestMapping("/pagos")
public class PagoController {
    
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoModel>> listarPagos() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoModel> obtenerPago(@PathVariable int id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagoModel> crearPago(@RequestBody PagoModel pago) {
        PagoModel creado = pagoService.crearPago(pago);
        return ResponseEntity.created(URI.create("/api/pagos/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoModel> actualizarPago(@PathVariable int id, @RequestBody PagoModel pago) {
        PagoModel actualizado = pagoService.actualizarPago(id, pago);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable int id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
