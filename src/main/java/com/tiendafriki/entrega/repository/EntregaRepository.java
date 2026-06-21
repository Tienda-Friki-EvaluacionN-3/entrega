package com.tiendafriki.entrega.repository;

import com.tiendafriki.entrega.model.Entrega;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

    List<Entrega> findByEnvioId(Integer envioId);

    Optional<Entrega> findByEnvioIdAndEstado(
            Integer envioId,
            String estado
    );

}
