package com.tiendafriki.entrega.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EntregaRequestDTO {

    @NotNull(message = "[ERROR] El id del envio no puede estar vacio [X_X] ...")
    private Integer envioId;

    @Pattern(
        regexp = "(?i)Entregado|Fallido",
        message = "[ERROR] El estado debe ser Entregado o Fallido [X_X] ..."
    )
    @NotBlank(message = "[ERROR] El estado no puede estar vacio [X_X] ...")
    private String estado;

}
