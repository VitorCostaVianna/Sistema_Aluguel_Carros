package com.sistemaaluguel.dto;

import com.sistemaaluguel.model.PedidoAluguel;

public record PedidoResponseDTO(ClienteResponseDTO cliente,
        AutomovelResponseDTO automovel) {
    public PedidoResponseDTO(PedidoAluguel pedido){
        this(new ClienteResponseDTO(pedido.getCliente()), new AutomovelResponseDTO(pedido.getAutomovel()));
    }
}
