package com.tiendafriki.entrega.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "entrega")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // === NUEVO === //
    // Relación lógica con el microservicio Envío

    private Integer envioId;

    // Fecha automática de creación de la entrega

    private LocalDate fechaEntrega;

    // Estado final del intento de entrega

    @Pattern(regexp = "(?i)Entregado|Fallido", message = "[+] El estado debe ser Entregado o Fallido [X_X] ...")
    private String estado;
    
}
