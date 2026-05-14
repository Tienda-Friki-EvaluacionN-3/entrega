package com.tiendafriki.entrega.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "entrega")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "[+] La fecha no puede estar vacia...")
    private LocalDate fechaEntrega;

    @Pattern(regexp = "(?i)Entregado|Pendiente|", message = "[+] El estado debe ser Entregado o Pendiente...")
    @NotBlank(message = "[+] El estado no puede estar vacio...")
    private String estado;
    
}
