package com.tiendafriki.entrega.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tiendafriki.entrega.dto.EntregaRequestDTO;
import com.tiendafriki.entrega.model.Entrega;
import com.tiendafriki.entrega.model.Envio;
import com.tiendafriki.entrega.repository.EntregaRepository;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository repository;

    public List<Entrega> listar() {

        return repository.findAll();
    }

    public Entrega buscarPorId(Integer id) {

        return repository.findById(id)

                .orElseThrow(() ->

                        new NoSuchElementException(
                                "[ERROR] Entrega no encontrada [X_X] ..."
                        )
                );
    }

    public List<Entrega> buscarxEnvio(Integer envioId) {

        List<Entrega> lista =
                repository.findByEnvioId(envioId);

        if (lista.isEmpty()) {

            throw new NoSuchElementException(
                    "[ERROR] No existen entregas para este envio [X_X] ..."
            );
        }

        return lista;
    }

    private Envio validarEnvio(Integer envioId) {

        RestTemplate rt = new RestTemplate();

        String url =
                "http://localhost:8088/envio/buscarId/"
                        + envioId;

        try {

            Envio envio =
                    rt.getForObject(
                            url,
                            Envio.class
                    );

            if (envio == null) {

                throw new NoSuchElementException(
                        "[ERROR] El envio no existe [X_X] ..."
                );
            }

            return envio;

        } catch (Exception e) {

            throw new RuntimeException(
                    "[ERROR] No se pudo validar el envio [X_X] ..."
            );
        }
    }

    public String guardar(
            EntregaRequestDTO dto
    ) {

        Envio envio =
                validarEnvio(dto.getEnvioId());

        if (!envio.getEstado()
                .equalsIgnoreCase("Enviado")) {

            throw new IllegalArgumentException(
                    "[ERROR] El envio debe estar ENVIADO para registrar una entrega [X_X] ..."
            );
        }

        Optional<Entrega> entregaExitosa =
                repository.findByEnvioIdAndEstado(
                        dto.getEnvioId(),
                        "Entregado"
                );

        if (entregaExitosa.isPresent()) {

            throw new IllegalArgumentException(
                    "[ERROR] Este envio ya fue entregado correctamente [X_X] ..."
            );
        }

        Entrega entrega =
                new Entrega();

        entrega.setEnvioId(
                dto.getEnvioId()
        );

        entrega.setFechaEntrega(
                LocalDate.now()
        );

        entrega.setEstado(
                dto.getEstado()
        );

        repository.save(entrega);

        return "[+] La entrega fue registrada correctamente";
    }

    public String eliminar(Integer id) {

        Optional<Entrega> entregaOpt = repository.findById(id);

        if (entregaOpt.isEmpty()) {

            throw new NoSuchElementException(
                    "[ERROR] Entrega no encontrada [X_X] ..."
            );
        }

        repository.deleteById(id);

        return "[+] La entrega fue eliminada correctamente";
    }
}