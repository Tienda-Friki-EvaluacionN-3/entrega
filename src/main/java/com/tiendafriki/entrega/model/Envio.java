package com.tiendafriki.entrega.model;

import lombok.Data;

@Data
public class Envio {

    private Integer id;

    private Integer pedidoId;

    private String estado;

}
