package com.sistemaaluguel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaaluguel.dto.AutomovelResponseDTO;
import com.sistemaaluguel.dto.CriarAutomovelDTO;
import com.sistemaaluguel.model.Automovel;
import com.sistemaaluguel.repository.AutomovelRepository;

@Service
public class AutomovelService {
    
    @Autowired
    private AutomovelRepository automovelRepository;

    public AutomovelResponseDTO criarAutomovel(CriarAutomovelDTO dto) {
        Automovel automovel = new Automovel();
        automovel.setAno(dto.ano());
        automovel.setMarca(dto.marca());
        automovel.setMatricula(dto.matricula());
        automovel.setModelo(dto.modelo());
        automovel.setPlaca(dto.placa());
        automovelRepository.save(automovel);

        return new AutomovelResponseDTO(automovel);
    }

}
