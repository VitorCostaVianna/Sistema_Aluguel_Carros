package com.sistemaaluguel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaaluguel.model.PedidoAluguel;

@Repository
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long>{
    
}
