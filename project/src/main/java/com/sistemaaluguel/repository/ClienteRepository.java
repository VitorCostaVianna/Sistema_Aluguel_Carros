package com.sistemaaluguel.repository;

import com.sistemaaluguel.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByEmail(String email);
    
    Optional<Cliente> findByCpf(String cpf);
    
    Optional<Cliente> findByRg(String rg);
    
    boolean existsByEmail(String email);
    
    boolean existsByCpf(String cpf);
    
    boolean existsByRg(String rg);
}
