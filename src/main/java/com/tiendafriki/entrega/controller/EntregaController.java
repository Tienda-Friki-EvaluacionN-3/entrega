package com.tiendafriki.entrega.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendafriki.entrega.dto.EntregaRequestDTO;
import com.tiendafriki.entrega.model.Entrega;
import com.tiendafriki.entrega.service.EntregaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar entregas", description = "Obtiene una lista con todas las entregas registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/listar")
    public List<Entrega> listar() {
        return service.listar();
    }

    @Operation(summary = "Buscar entrega por ID", description = "Busca una entrega vinculado a su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Entrega no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/buscarId/{id}")
    public Entrega buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Buscar entregas por envío", description = "Obtiene las entregas vinculada a un ID de envio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entregas asociadas al envío obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron entregas para el envío indicado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/buscarxEnvio/{envioId}")
    public List<Entrega> buscarxEnvio(
            @PathVariable Integer envioId) {

        return service.buscarxEnvio(envioId);
    }

    @Operation(summary = "Agregar entrega", description = "Agregar una nueva entrega. Estados: Entregado|Fallido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrega registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping("/agregar")
    public ResponseEntity<String> crearPedido(@Valid @RequestBody EntregaRequestDTO dto) {
        String mensaje = service.guardar(dto);
        return ResponseEntity.status(201).body(mensaje);
    }

    @Operation(summary = "Eliminar entrega", description = "Elimina una entrega vinculado a su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Entrega no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String mensaje = service.eliminar(id);
        return ResponseEntity.status(200).body(mensaje);

    }

}
