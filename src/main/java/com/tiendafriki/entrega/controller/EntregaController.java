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

import com.tiendafriki.entrega.dto.EntregaRequestDTO;
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

    // === LISTAR === //

    @GetMapping("/listar")
    public List<Entrega> listar() {
        return service.listar();
    }

    // === BUSCAR POR ID === //

    @GetMapping("/buscarId/{id}")
    public Entrega buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    // === BUSCAR POR ENVIO === //

    @GetMapping("/buscarxEnvio/{envioId}")
    public List<Entrega> buscarxEnvio(
            @PathVariable Integer envioId
    ) {

        return service.buscarxEnvio(envioId);
    }

    // === CREAR ENTREGA === //

    @PostMapping("/agregar")
    public ResponseEntity<String> crearPedido(@Valid @RequestBody EntregaRequestDTO dto) {
        String mensaje = service.guardar(dto);
        return ResponseEntity.status(201).body(mensaje);
    }

     // === ELIMINAR === //

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String mensaje = service.eliminar(id);
        return ResponseEntity.status(200).body(mensaje);

    }

    /*

    // Se eliminó actualizar, ya que los datos de una entrega no deberian ser modificados
    // Una Entrega solo se ingresa como Fallida o Entregada, su fecha es automatica,
    // y su id de pedido no puede ser cambiado, con el fin de proteger integridad de los datos
    // trazabilidad y control de la logistica

    // Entrega solo representa un estado final
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@Valid @RequestBody Entrega entrega) {
        String mensaje = service.actualizar(entrega);
        return ResponseEntity.status(200).body(mensaje);
    }
    
    */

}
