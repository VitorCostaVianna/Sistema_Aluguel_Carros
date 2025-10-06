package com.sistemaaluguel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaaluguel.model.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    
}
