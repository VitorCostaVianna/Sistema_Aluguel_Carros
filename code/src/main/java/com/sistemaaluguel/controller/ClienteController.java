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

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            ClienteResponseDTO cliente = clienteService.buscarClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.cadastrarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.atualizarCliente(id, clienteDTO);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok(Map.of("mensagem", "Cliente deletado com sucesso"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
