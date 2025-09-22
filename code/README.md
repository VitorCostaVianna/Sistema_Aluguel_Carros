# Sistema de Aluguel de Carros

Sistema desenvolvido em Spring Boot com autenticação JWT para gerenciamento de clientes.

## Funcionalidades Implementadas

### Autenticação
- **Registro de Cliente**: `POST /api/auth/registro`
- **Login**: `POST /api/auth/login`

### CRUD de Cliente
- **Listar Clientes**: `GET /api/clientes`
- **Buscar Cliente por ID**: `GET /api/clientes/{id}`
- **Cadastrar Cliente**: `POST /api/clientes`
- **Atualizar Cliente**: `PUT /api/clientes/{id}`
- **Deletar Cliente**: `DELETE /api/clientes/{id}`

## Tecnologias Utilizadas

- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- MySQL Database
- Maven

## Como Executar

1. **Pré-requisitos**:
   - Java 17 ou superior
   - Maven 3.6 ou superior
   - MySQL 8.0 ou superior

2. **Configurar o banco de dados**:
   - Instalar e configurar MySQL
   - Criar um banco de dados (opcional - será criado automaticamente)
   - Configurar usuário e senha no arquivo `application.yml`

3. **Executar a aplicação**:
   ```bash
   cd project
   mvn spring-boot:run
   ```

4. **Acessar a aplicação**:
   - API: http://localhost:8080

## Estrutura do Projeto

```
src/main/java/com/sistemaaluguel/
├── config/
│   ├── JwtConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   └── ClienteController.java
├── dto/
│   ├── ClienteDTO.java
│   ├── ClienteResponseDTO.java
│   └── LoginDTO.java
├── entity/
│   ├── Cliente.java
│   ├── EntidadesEmpregadoras.java
│   └── Rendimentos.java
├── repository/
│   └── ClienteRepository.java
├── security/
│   └── JwtAuthenticationFilter.java
├── service/
│   ├── AuthService.java
│   └── ClienteService.java
└── SistemaAluguelCarrosApplication.java
```

## Exemplos de Uso

### 1. Registrar um Cliente
```bash
curl -X POST http://localhost:8080/api/auth/registro \
  -H "Content-Type: application/json" \
  -d '{
    "rg": "123456789",
    "cpf": "12345678901",
    "nome": "João Silva",
    "endereco": "Rua das Flores, 123",
    "profissao": "Engenheiro",
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

### 2. Fazer Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

### 3. Listar Clientes (com autenticação)
```bash
curl -X GET http://localhost:8080/api/clientes \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

## Segurança

- Todas as rotas de CRUD de cliente requerem autenticação JWT
- As rotas de login e registro são públicas
- Senhas são criptografadas usando BCrypt
- Tokens JWT têm validade de 24 horas

## Banco de Dados

O projeto utiliza MySQL Database. As tabelas são criadas automaticamente baseadas nas entidades JPA:

- `clientes`: Dados dos clientes
- `entidades_empregadoras`: Entidades empregadoras dos clientes
- `rendimentos`: Rendimentos dos clientes

### Configuração do MySQL

Por padrão, a aplicação está configurada para conectar ao MySQL com:
- **Host**: localhost:3306
- **Database**: sistema_aluguel_carros (criado automaticamente)
- **Username**: root
- **Password**: root

Para alterar essas configurações, edite o arquivo `src/main/resources/application.yml`.

## Próximos Passos

- Implementar CRUD para EntidadesEmpregadoras
- Implementar CRUD para Rendimentos
- Adicionar validações de CPF e CNPJ
- Implementar testes unitários
- Adicionar documentação com Swagger/OpenAPI
