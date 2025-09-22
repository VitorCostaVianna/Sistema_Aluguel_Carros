# Testes do Sistema de Aluguel de Carros

Este diretório contém todos os testes automatizados para o sistema de aluguel de carros.

## Estrutura dos Testes

### 1. Testes de Controllers (`controller/`)
- **AuthControllerTest**: Testa endpoints de autenticação (registro e login)
- **ClienteControllerTest**: Testa endpoints de CRUD de clientes com autenticação JWT

### 2. Testes de Integração (`integration/`)
- **IntegrationTest**: Testa fluxos completos da aplicação, incluindo autenticação e operações CRUD

### 3. Testes de Segurança (`security/`)
- **JwtSecurityTest**: Testa especificamente a segurança JWT e autenticação

### 4. Utilitários de Teste (`util/`)
- **TestDataBuilder**: Cria dados de teste válidos e inválidos
- **TestJwtUtil**: Gera tokens JWT para testes

### 5. Configurações de Teste (`config/`)
- **TestConfig**: Configurações específicas para testes

## Como Executar os Testes

### Executar todos os testes
```bash
mvn test
```

### Executar testes específicos
```bash
# Testes de controllers
mvn test -Dtest=*ControllerTest

# Testes de integração
mvn test -Dtest=*IntegrationTest

# Testes de segurança
mvn test -Dtest=*SecurityTest
```

### Executar com relatório de cobertura
```bash
mvn test jacoco:report
```

## Configuração de Teste

Os testes utilizam:
- **H2 Database**: Banco de dados em memória para testes
- **MockMvc**: Para simular requisições HTTP
- **JWT**: Tokens de autenticação para testes de segurança
- **Spring Security Test**: Para testes de autenticação e autorização

## Cobertura de Testes

Os testes cobrem:

### AuthController
- ✅ Registro de cliente com dados válidos
- ✅ Registro com dados inválidos
- ✅ Registro com email duplicado
- ✅ Login com credenciais válidas
- ✅ Login com credenciais inválidas
- ✅ Login com usuário não encontrado
- ✅ Login com senha incorreta

### ClienteController
- ✅ Listar clientes (com e sem token)
- ✅ Buscar cliente por ID (com e sem token)
- ✅ Cadastrar cliente (com e sem token)
- ✅ Atualizar cliente (com e sem token)
- ✅ Deletar cliente (com e sem token)
- ✅ Tratamento de erros (cliente não encontrado, dados inválidos)

### Segurança JWT
- ✅ Acesso sem token
- ✅ Acesso com token inválido
- ✅ Acesso com token malformado
- ✅ Acesso com token expirado
- ✅ Acesso com token válido
- ✅ Endpoints públicos (não requerem autenticação)

### Integração
- ✅ Fluxo completo: registro → login → operações CRUD
- ✅ Tratamento de erros em fluxos completos
- ✅ Validação de tokens em operações sequenciais

## Dados de Teste

Os dados de teste são criados usando o `TestDataBuilder` que fornece:
- Clientes válidos e inválidos
- Dados de login válidos e inválidos
- Tokens JWT para testes

## Configuração do Ambiente de Teste

O arquivo `application-test.yml` configura:
- Banco H2 em memória
- Configurações JWT para teste
- Logs de debug para desenvolvimento

## Notas Importantes

1. **Tokens JWT**: Os testes utilizam tokens JWT válidos gerados especificamente para testes
2. **Banco de Dados**: Cada teste roda com um banco limpo (H2 em memória)
3. **Isolamento**: Os testes são independentes e podem ser executados em qualquer ordem
4. **Segurança**: Todos os endpoints protegidos são testados com e sem autenticação
