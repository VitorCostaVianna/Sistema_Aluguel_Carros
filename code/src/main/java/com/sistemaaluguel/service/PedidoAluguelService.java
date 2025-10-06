package com.sistemaaluguel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaaluguel.dto.CriarPedidoDTO;
import com.sistemaaluguel.dto.PedidoResponseDTO;
import com.sistemaaluguel.model.Automovel;
import com.sistemaaluguel.model.Cliente;
import com.sistemaaluguel.model.PedidoAluguel;
import com.sistemaaluguel.model.StatusPedido;
import com.sistemaaluguel.repository.AutomovelRepository;
import com.sistemaaluguel.repository.ClienteRepository;
import com.sistemaaluguel.repository.PedidoAluguelRepository;

@Service
public class PedidoAluguelService {
    
    @Autowired
    private PedidoAluguelRepository pedidoAluguelRepository;

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public PedidoResponseDTO criarPedido(CriarPedidoDTO dto){
        Automovel automovel = automovelRepository.findById(dto.automovelId())
                    .orElseThrow(() -> new RuntimeException("Automovel não encontrado!"));

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));  
                    
        PedidoAluguel novoPedido = new PedidoAluguel();
        novoPedido.setAutomovel(automovel);
        novoPedido.setCliente(cliente);
        novoPedido.setStatusPedido(StatusPedido.EM_ANALISE);
        pedidoAluguelRepository.save(novoPedido);

        return new PedidoResponseDTO(novoPedido);
    }

    public String visualizarStatus(Long pedidoId){
        PedidoAluguel pedido = pedidoAluguelRepository.findById(pedidoId)
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));

        return pedido.getStatusPedido().toString();
    }

}
