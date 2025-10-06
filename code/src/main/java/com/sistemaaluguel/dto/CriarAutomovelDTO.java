package com.sistemaaluguel.dto;

import java.time.LocalDate;

import com.sistemaaluguel.model.TipoProprietario;

public record CriarAutomovelDTO(String matricula,
        LocalDate ano,
        String marca,
        String modelo,
        String placa) {
    
}
