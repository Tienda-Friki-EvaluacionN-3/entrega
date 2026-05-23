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

    // === LISTAR === //

    public List<Entrega> listar() {

        return repository.findAll();
    }

    // === BUSCAR POR ID === //

    public Entrega buscarPorId(Integer id) {

        return repository.findById(id)

                .orElseThrow(() ->

                        new NoSuchElementException(
                                "[ERROR] Entrega no encontrada [X_X] ..."
                        )
                );
    }

    // === BUSCAR POR ENVIO === //

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

    // === VALIDAR ENVIO === //

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

    // === GUARDAR ENTREGA === //

    public String guardar(
            EntregaRequestDTO dto
    ) {

        // Validar existencia del envío

        Envio envio =
                validarEnvio(dto.getEnvioId());

        // Validar estado del envío

        if (!envio.getEstado()
                .equalsIgnoreCase("Enviado")) {

            throw new IllegalArgumentException(
                    "[ERROR] El envio debe estar ENVIADO para registrar una entrega [X_X] ..."
            );
        }

        // Validar que no exista una entrega exitosa

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

        // Crear nueva entrega

        Entrega entrega =
                new Entrega();

        entrega.setEnvioId(
                dto.getEnvioId()
        );

        // Fecha automática

        entrega.setFechaEntrega(
                LocalDate.now()
        );

        entrega.setEstado(
                dto.getEstado()
        );

        repository.save(entrega);

        return "[+] La entrega fue registrada correctamente";
    }

    // === ELIMINAR === //

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

/*

package com.tiendafriki.entrega.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendafriki.entrega.model.Entrega;
import com.tiendafriki.entrega.repository.EntregaRepository;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository repository;

    public List<Entrega> listar() {
        return repository.findAll();
    }

    public Optional<Entrega> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public String guardar(Entrega entrega) {
        repository.save(entrega);
        return "[+] La entrega fue agregada correctamente";
    }

    public String actualizar(Entrega entrega) {
        List<Entrega> lista = repository.findAll();
        for (Entrega p : lista) {
            if (p.getId().equals(entrega.getId())) {
                repository.save(entrega);
                return "[+] La entrega fue actualizada correctamente";
            }
        }
        return "[+] La entrega no fue encontrada";

    }

    public String eliminar(Integer id) {
        List<Entrega> lista = repository.findAll();
        for (Entrega p : lista) {
            if (p.getId().equals(id)) {
                repository.deleteById(id);
                return "[+] La entrega fue eliminada correctamente";
            }

        }
        return "[+] La entrega no fue encontrada";
    }
}


*/
