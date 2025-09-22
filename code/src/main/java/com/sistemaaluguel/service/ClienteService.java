package com.sistemaaluguel.service;

import com.sistemaaluguel.dto.ClienteDTO;
import com.sistemaaluguel.dto.ClienteResponseDTO;
import com.sistemaaluguel.model.Cliente;
import com.sistemaaluguel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements UserDetailsService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado com email: " + email));
    }
    
    public ClienteResponseDTO cadastrarCliente(ClienteDTO clienteDTO) {
        // Verificar se já existe cliente com email, CPF ou RG
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este email");
        }
        
        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF");
        }
        
        if (clienteRepository.existsByRg(clienteDTO.getRg())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este RG");
        }
        
        // Criar novo cliente
        Cliente cliente = new Cliente();
        cliente.setRg(clienteDTO.getRg());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));
        
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return convertToResponseDTO(clienteSalvo);
    }
    
    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return convertToResponseDTO(cliente);
    }
    
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        
        // Verificar se email, CPF ou RG já existem em outros clientes
        if (!cliente.getEmail().equals(clienteDTO.getEmail()) && 
            clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este email");
        }
        
        if (!cliente.getCpf().equals(clienteDTO.getCpf()) && 
            clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF");
        }
        
        if (!cliente.getRg().equals(clienteDTO.getRg()) && 
            clienteRepository.existsByRg(clienteDTO.getRg())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este RG");
        }
        
        // Atualizar dados do cliente
        cliente.setRg(clienteDTO.getRg());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        cliente.setEmail(clienteDTO.getEmail());
        
        // Atualizar senha apenas se fornecida
        if (clienteDTO.getSenha() != null && !clienteDTO.getSenha().isEmpty()) {
            cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));
        }
        
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return convertToResponseDTO(clienteAtualizado);
    }
    
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
    
    private ClienteResponseDTO convertToResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getRg(),
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEndereco(),
                cliente.getProfissao(),
                cliente.getEmail()
        );
    }
}
