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
