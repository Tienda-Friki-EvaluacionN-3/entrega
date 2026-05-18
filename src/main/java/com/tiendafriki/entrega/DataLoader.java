package com.tiendafriki.entrega;

import com.tiendafriki.entrega.model.Entrega;
import com.tiendafriki.entrega.repository.EntregaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(EntregaRepository repositoryEntrega) {
        return args -> {
            if (repositoryEntrega.count() == 0) {
                
                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 1),
                        "Entregado"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 2),
                        "Pendiente"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 3),
                        "Entregado"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 4),
                        "Pendiente"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 5),
                        "Entregado"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 6),
                        "Pendiente"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 7),
                        "Entregado"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 8),
                        "Pendiente"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 9),
                        "Entregado"));

                repositoryEntrega.save(new Entrega(
                        null,
                        LocalDate.of(2026, 5, 10),
                        "Pendiente"));
            }
        };
    }

}
