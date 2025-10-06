package com.sistemaaluguel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaaluguel.dto.CriarPedidoDTO;
import com.sistemaaluguel.dto.PedidoResponseDTO;
import com.sistemaaluguel.service.PedidoAluguelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos de Aluguel", description = "Endpoints para criação e visualização de pedidos de aluguel de automóveis")
public class PedidoAluguelController {

    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    // ============================
    // Criar Pedido
    // ============================
    @Operation(summary = "Cria um novo pedido de aluguel", description = "Permite que um cliente crie um pedido de aluguel de automóvel.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente ou automóvel não encontrado"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody CriarPedidoDTO dto) {
        PedidoResponseDTO response = pedidoAluguelService.criarPedido(dto);
        return ResponseEntity.ok(response);
    }

    // ============================
    // Visualizar Status do Pedido
    // ============================
    @Operation(summary = "Consulta o status de um pedido", description = "Retorna o status atual do pedido informado pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status retornado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}/status")
    public ResponseEntity<String> visualizarStatus(@PathVariable Long id) {
        String status = pedidoAluguelService.visualizarStatus(id);
        return ResponseEntity.ok(status);
    }
}
