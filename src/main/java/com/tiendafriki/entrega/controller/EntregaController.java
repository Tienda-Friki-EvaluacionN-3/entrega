package com.tiendafriki.entrega.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendafriki.entrega.model.Entrega;
import com.tiendafriki.entrega.service.EntregaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<Entrega> listar() {
        return service.listar();
    }

    @GetMapping("/buscarId/{id}")
    public Optional<Entrega> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> crearPedido(@Valid @RequestBody Entrega entrega) {
        String mensaje = service.guardar(entrega);
        return ResponseEntity.status(201).body(mensaje);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@Valid @RequestBody Entrega entrega) {
        String mensaje = service.actualizar(entrega);
        return ResponseEntity.status(200).body(mensaje);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String mensaje = service.eliminar(id);
        return ResponseEntity.status(200).body(mensaje);

    }

}
