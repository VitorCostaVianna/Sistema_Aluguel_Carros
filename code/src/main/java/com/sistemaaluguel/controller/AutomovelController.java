package com.sistemaaluguel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemaaluguel.dto.AutomovelResponseDTO;
import com.sistemaaluguel.dto.CriarAutomovelDTO;
import com.sistemaaluguel.service.AutomovelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/automoveis")
@Tag(name = "Automóveis", description = "Endpoints para gerenciamento de automóveis disponíveis para aluguel")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    // ============================
    // Criar Automóvel
    // ============================
    @Operation(summary = "Cria um novo automóvel", description = "Cadastra um novo automóvel no sistema, informando seus dados básicos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Automóvel criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na requisição ou dados inválidos")
    })
    @PostMapping
    public ResponseEntity<AutomovelResponseDTO> criarAutomovel(@RequestBody CriarAutomovelDTO dto) {
        AutomovelResponseDTO response = automovelService.criarAutomovel(dto);
        return ResponseEntity.ok(response);
    }

}
