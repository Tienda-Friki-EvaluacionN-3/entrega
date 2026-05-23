package com.tiendafriki.entrega.repository;

import com.tiendafriki.entrega.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

    // Buscar entregas asociadas a un envío

    List<Entrega> findByEnvioId(Integer envioId);

    // Buscar entrega exitosa asociada al envío

    Optional<Entrega> findByEnvioIdAndEstado(
            Integer envioId,
            String estado
    );

}
