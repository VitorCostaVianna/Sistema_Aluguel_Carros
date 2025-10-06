package com.sistemaaluguel.dto;

import java.time.LocalDate;

import com.sistemaaluguel.model.Automovel;

public record AutomovelResponseDTO(String matricula,
        LocalDate ano,
        String marca,
        String modelo,
        String placa) {
    public AutomovelResponseDTO(Automovel automovel) {
        this(automovel.getMatricula(),
                automovel.getAno(),
                automovel.getMarca(),
                automovel.getModelo(),
                automovel.getPlaca());
    }
}
