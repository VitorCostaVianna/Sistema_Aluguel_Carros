package com.sistemaaluguel.controller;

import com.sistemaaluguel.dto.ClienteDTO;
import com.sistemaaluguel.dto.ClienteResponseDTO;
import com.sistemaaluguel.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@Tag(name = "Clientes", description = "CRUD de Clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna todos os clientes")
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            ClienteResponseDTO cliente = clienteService.buscarClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Cadastrar cliente")
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.cadastrarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.atualizarCliente(id, clienteDTO);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok(Map.of("mensagem", "Cliente deletado com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
