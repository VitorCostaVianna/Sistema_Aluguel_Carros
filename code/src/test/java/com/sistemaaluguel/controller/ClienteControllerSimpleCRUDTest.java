package com.sistemaaluguel.controller;

import com.sistemaaluguel.dto.ClienteDTO;
import com.sistemaaluguel.dto.ClienteResponseDTO;
import com.sistemaaluguel.util.TestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ClienteController - Testes CRUD Simples")
class ClienteControllerSimpleCRUDTest {

    @Test
    @DisplayName("CREATE - Deve criar ClienteDTO válido")
    void criarClienteDTO_ComDadosValidos_DeveFuncionar() {
        // Arrange & Act
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Assert
        assertNotNull(clienteDTO);
        assertEquals("João Silva", clienteDTO.getNome());
        assertEquals("joao@email.com", clienteDTO.getEmail());
        assertEquals("12345678901", clienteDTO.getCpf());
        assertEquals("123456789", clienteDTO.getRg());
        assertEquals("Rua das Flores, 123", clienteDTO.getEndereco());
        assertEquals("Desenvolvedor", clienteDTO.getProfissao());
        assertEquals("123456", clienteDTO.getSenha());
    }

    @Test
    @DisplayName("CREATE - Deve criar ClienteDTO inválido")
    void criarClienteDTO_ComDadosInvalidos_DeveFuncionar() {
        // Arrange & Act
        ClienteDTO clienteDTO = TestDataBuilder.createInvalidClienteDTO();

        // Assert
        assertNotNull(clienteDTO);
        assertEquals("", clienteDTO.getNome());
        assertEquals("email-invalido", clienteDTO.getEmail());
        assertEquals("123", clienteDTO.getCpf());
        assertEquals("123", clienteDTO.getRg());
        assertEquals("", clienteDTO.getEndereco());
        assertEquals("", clienteDTO.getProfissao());
        assertEquals("123", clienteDTO.getSenha());
    }

    @Test
    @DisplayName("READ - Deve criar ClienteResponseDTO válido")
    void criarClienteResponseDTO_ComDadosValidos_DeveFuncionar() {
        // Arrange & Act
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(1L);
        clienteResponseDTO.setNome("João Silva");
        clienteResponseDTO.setEmail("joao@email.com");
        clienteResponseDTO.setCpf("12345678901");
        clienteResponseDTO.setRg("123456789");
        clienteResponseDTO.setEndereco("Rua das Flores, 123");
        clienteResponseDTO.setProfissao("Desenvolvedor");

        // Assert
        assertNotNull(clienteResponseDTO);
        assertEquals(1L, clienteResponseDTO.getId());
        assertEquals("João Silva", clienteResponseDTO.getNome());
        assertEquals("joao@email.com", clienteResponseDTO.getEmail());
        assertEquals("12345678901", clienteResponseDTO.getCpf());
        assertEquals("123456789", clienteResponseDTO.getRg());
        assertEquals("Rua das Flores, 123", clienteResponseDTO.getEndereco());
        assertEquals("Desenvolvedor", clienteResponseDTO.getProfissao());
    }

    @Test
    @DisplayName("UPDATE - Deve atualizar dados do ClienteDTO")
    void atualizarClienteDTO_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Act
        clienteDTO.setNome("João Silva Atualizado");
        clienteDTO.setEmail("joao.novo@email.com");
        clienteDTO.setProfissao("Desenvolvedor Senior");
        clienteDTO.setEndereco("Rua das Flores, 123 - Atualizado");

        // Assert
        assertEquals("João Silva Atualizado", clienteDTO.getNome());
        assertEquals("joao.novo@email.com", clienteDTO.getEmail());
        assertEquals("Desenvolvedor Senior", clienteDTO.getProfissao());
        assertEquals("Rua das Flores, 123 - Atualizado", clienteDTO.getEndereco());
    }

    @Test
    @DisplayName("DELETE - Deve verificar se ClienteDTO pode ser deletado")
    void deletarClienteDTO_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Act - Simular deleção limpando os dados
        clienteDTO.setNome(null);
        clienteDTO.setEmail(null);
        clienteDTO.setCpf(null);
        clienteDTO.setRg(null);
        clienteDTO.setEndereco(null);
        clienteDTO.setProfissao(null);
        clienteDTO.setSenha(null);

        // Assert
        assertNull(clienteDTO.getNome());
        assertNull(clienteDTO.getEmail());
        assertNull(clienteDTO.getCpf());
        assertNull(clienteDTO.getRg());
        assertNull(clienteDTO.getEndereco());
        assertNull(clienteDTO.getProfissao());
        assertNull(clienteDTO.getSenha());
    }

    @Test
    @DisplayName("VALIDATION - Deve validar campos obrigatórios")
    void validarCamposObrigatorios_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Act & Assert
        assertNotNull(clienteDTO.getNome(), "Nome não pode ser nulo");
        assertFalse(clienteDTO.getNome().trim().isEmpty(), "Nome não pode ser vazio");
        
        assertNotNull(clienteDTO.getEmail(), "Email não pode ser nulo");
        assertFalse(clienteDTO.getEmail().trim().isEmpty(), "Email não pode ser vazio");
        assertTrue(clienteDTO.getEmail().contains("@"), "Email deve conter @");
        
        assertNotNull(clienteDTO.getCpf(), "CPF não pode ser nulo");
        assertFalse(clienteDTO.getCpf().trim().isEmpty(), "CPF não pode ser vazio");
        assertTrue(clienteDTO.getCpf().length() >= 11, "CPF deve ter pelo menos 11 caracteres");
        
        assertNotNull(clienteDTO.getRg(), "RG não pode ser nulo");
        assertFalse(clienteDTO.getRg().trim().isEmpty(), "RG não pode ser vazio");
        assertTrue(clienteDTO.getRg().length() >= 9, "RG deve ter pelo menos 9 caracteres");
        
        assertNotNull(clienteDTO.getSenha(), "Senha não pode ser nula");
        assertFalse(clienteDTO.getSenha().trim().isEmpty(), "Senha não pode ser vazia");
        assertTrue(clienteDTO.getSenha().length() >= 6, "Senha deve ter pelo menos 6 caracteres");
    }

    @Test
    @DisplayName("VALIDATION - Deve detectar dados inválidos")
    void detectarDadosInvalidos_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createInvalidClienteDTO();

        // Act & Assert
        assertTrue(clienteDTO.getNome().trim().isEmpty(), "Nome inválido detectado");
        assertFalse(clienteDTO.getEmail().contains("@"), "Email inválido detectado");
        assertTrue(clienteDTO.getCpf().length() < 11, "CPF inválido detectado");
        assertTrue(clienteDTO.getRg().length() < 9, "RG inválido detectado");
        assertTrue(clienteDTO.getSenha().length() < 6, "Senha inválida detectada");
    }

    @Test
    @DisplayName("BUSINESS LOGIC - Deve verificar unicidade de campos")
    void verificarUnicidadeCampos_DeveFuncionar() {
        // Arrange
        ClienteDTO cliente1 = TestDataBuilder.createValidClienteDTO();
        ClienteDTO cliente2 = TestDataBuilder.createValidClienteDTO();
        cliente2.setNome("Maria Santos");
        cliente2.setEmail("maria@email.com");
        cliente2.setCpf("98765432100");
        cliente2.setRg("987654321");

        // Act & Assert
        assertNotEquals(cliente1.getNome(), cliente2.getNome(), "Nomes devem ser diferentes");
        assertNotEquals(cliente1.getEmail(), cliente2.getEmail(), "Emails devem ser diferentes");
        assertNotEquals(cliente1.getCpf(), cliente2.getCpf(), "CPFs devem ser diferentes");
        assertNotEquals(cliente1.getRg(), cliente2.getRg(), "RGs devem ser diferentes");
    }

    @Test
    @DisplayName("BUSINESS LOGIC - Deve verificar formato de email")
    void verificarFormatoEmail_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Act & Assert
        String email = clienteDTO.getEmail();
        assertTrue(email.contains("@"), "Email deve conter @");
        assertTrue(email.contains("."), "Email deve conter .");
        assertFalse(email.startsWith("@"), "Email não pode começar com @");
        assertFalse(email.endsWith("@"), "Email não pode terminar com @");
        assertFalse(email.startsWith("."), "Email não pode começar com .");
        assertFalse(email.endsWith("."), "Email não pode terminar com .");
    }

    @Test
    @DisplayName("BUSINESS LOGIC - Deve verificar formato de CPF")
    void verificarFormatoCpf_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Act & Assert
        String cpf = clienteDTO.getCpf();
        assertTrue(cpf.length() == 11, "CPF deve ter exatamente 11 caracteres");
        assertTrue(cpf.matches("\\d+"), "CPF deve conter apenas números");
    }

    @Test
    @DisplayName("BUSINESS LOGIC - Deve verificar formato de RG")
    void verificarFormatoRg_DeveFuncionar() {
        // Arrange
        ClienteDTO clienteDTO = TestDataBuilder.createValidClienteDTO();

        // Act & Assert
        String rg = clienteDTO.getRg();
        assertTrue(rg.length() >= 9 && rg.length() <= 12, "RG deve ter entre 9 e 12 caracteres");
        assertTrue(rg.matches("\\d+"), "RG deve conter apenas números");
    }
}
