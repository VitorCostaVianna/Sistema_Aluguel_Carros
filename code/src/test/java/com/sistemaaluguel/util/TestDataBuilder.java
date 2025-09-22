package com.sistemaaluguel.util;

import com.sistemaaluguel.dto.ClienteDTO;
import com.sistemaaluguel.dto.LoginDTO;
import com.sistemaaluguel.model.Cliente;
import com.sistemaaluguel.model.EntidadesEmpregadoras;
import com.sistemaaluguel.model.Rendimentos;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    
    public static ClienteDTO createValidClienteDTO() {
        ClienteDTO dto = new ClienteDTO();
        dto.setRg("123456789");
        dto.setCpf("12345678901");
        dto.setNome("João Silva");
        dto.setEndereco("Rua das Flores, 123");
        dto.setProfissao("Desenvolvedor");
        dto.setEmail("joao@email.com");
        dto.setSenha("123456");
        return dto;
    }
    
    public static ClienteDTO createInvalidClienteDTO() {
        ClienteDTO dto = new ClienteDTO();
        dto.setRg("123");
        dto.setCpf("123");
        dto.setNome("");
        dto.setEndereco("");
        dto.setProfissao("");
        dto.setEmail("email-invalido");
        dto.setSenha("123");
        return dto;
    }
    
    public static LoginDTO createValidLoginDTO() {
        return new LoginDTO("joao@email.com", "123456");
    }
    
    public static LoginDTO createInvalidLoginDTO() {
        return new LoginDTO("email-invalido", "");
    }
    
    public static Cliente createValidCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setRg("123456789");
        cliente.setCpf("12345678901");
        cliente.setNome("João Silva");
        cliente.setEndereco("Rua das Flores, 123");
        cliente.setProfissao("Desenvolvedor");
        cliente.setEmail("joao@email.com");
        cliente.setSenha("$2a$10$encodedPassword");
        
        List<EntidadesEmpregadoras> entidades = new ArrayList<>();
        EntidadesEmpregadoras entidade = new EntidadesEmpregadoras();
        entidade.setId(1L);
        entidade.setNome("Tech Corp");
        entidade.setCnpj("12345678000199");
        entidade.setCliente(cliente);
        entidades.add(entidade);
        cliente.setEntidadesEmpregadoras(entidades);
        
        List<Rendimentos> rendimentos = new ArrayList<>();
        Rendimentos rendimento = new Rendimentos();
        rendimento.setId(1L);
        rendimento.setCliente(cliente);
        rendimentos.add(rendimento);
        cliente.setRendimentos(rendimentos);
        
        return cliente;
    }
    
    public static List<Cliente> createValidClienteList() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(createValidCliente());
        
        Cliente cliente2 = createValidCliente();
        cliente2.setId(2L);
        cliente2.setRg("987654321");
        cliente2.setCpf("98765432100");
        cliente2.setNome("Maria Santos");
        cliente2.setEmail("maria@email.com");
        clientes.add(cliente2);
        
        return clientes;
    }
}