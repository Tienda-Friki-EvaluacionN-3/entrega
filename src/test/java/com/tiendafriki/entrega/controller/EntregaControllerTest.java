package com.tiendafriki.entrega.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tiendafriki.entrega.dto.EntregaRequestDTO;
import com.tiendafriki.entrega.model.Entrega;
import com.tiendafriki.entrega.service.EntregaService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EntregaController.class)
public class EntregaControllerTest {

     @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EntregaService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Entrega crearEntrega() {
        return new Entrega(
                1,
                1,
                LocalDate.now(),
                "Entregado"
        );
    }

    private EntregaRequestDTO crearEntregaRequestDTO() {

        EntregaRequestDTO dto = new EntregaRequestDTO();
        dto.setEnvioId(1);
        dto.setEstado("Entregado");

        return dto;
    }

    @Test
    void listarEntregas() throws Exception {

        when(service.listar())
                .thenReturn(List.of(crearEntrega()));

        mockMvc.perform(get("/entrega/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarEntregaPorId() throws Exception {

        when(service.buscarPorId(1))
                .thenReturn(crearEntrega());

        mockMvc.perform(get("/entrega/buscarId/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarEntregaPorEnvio() throws Exception {

        when(service.buscarxEnvio(1))
                .thenReturn(List.of(crearEntrega()));

        mockMvc.perform(get("/entrega/buscarxEnvio/1"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarEntrega() throws Exception {

        when(service.guardar(any()))
                .thenReturn("[+] La entrega fue registrada correctamente");

        mockMvc.perform(post("/entrega/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crearEntregaRequestDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    void eliminarEntrega() throws Exception {

        when(service.eliminar(1))
                .thenReturn("[+] La entrega fue eliminada correctamente");

        mockMvc.perform(delete("/entrega/eliminar/1"))
                .andExpect(status().isOk());
    }


}
