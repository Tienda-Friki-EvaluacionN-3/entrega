package com.tiendafriki.entrega.model;

import lombok.Data;

// Modelo Auxiliar de Envio

// Servirá para contener los datos que necesitamos de Envio para
// las validaciones del service de entrega
@Data
public class Envio {

    private Integer id;

    private Integer pedidoId;

    private String estado;

}
