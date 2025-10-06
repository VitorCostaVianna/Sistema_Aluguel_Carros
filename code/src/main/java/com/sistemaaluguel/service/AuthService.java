package com.sistemaaluguel.service;

import com.sistemaaluguel.config.JwtConfig;
import com.sistemaaluguel.dto.ClienteDTO;
import com.sistemaaluguel.dto.ClienteResponseDTO;
import com.sistemaaluguel.dto.LoginDTO;
import com.sistemaaluguel.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtConfig jwtConfig;
    
    public ClienteResponseDTO registrar(ClienteDTO clienteDTO) {
        return clienteService.cadastrarCliente(clienteDTO);
    }
    
    public Map<String, Object> login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha())
            );
            
            Cliente cliente = (Cliente) authentication.getPrincipal();
            String token = jwtConfig.generateToken(cliente);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("cliente", new ClienteResponseDTO(cliente));
            
            return response;
            
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email ou senha inválidos");
        }
    }
}
