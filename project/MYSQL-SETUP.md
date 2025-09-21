# Configuração do MySQL para o Sistema de Aluguel de Carros

## Pré-requisitos

1. **MySQL Server** instalado e rodando
2. **MySQL Workbench** ou **phpMyAdmin** (opcional, para gerenciamento)

## Configuração

### 1. Instalar MySQL

Se ainda não tiver o MySQL instalado:

**Windows:**
- Baixe o MySQL Installer: https://dev.mysql.com/downloads/installer/
- Execute o instalador e siga as instruções
- Configure a senha do usuário `root`

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

**macOS:**
```bash
brew install mysql
brew services start mysql
```

### 2. Configurar o Banco de Dados

**Opção 1: Automática (Recomendada)**
- A aplicação criará automaticamente o banco `sistema_aluguel_carros` quando iniciar
- Certifique-se de que o MySQL está rodando na porta 3306

**Opção 2: Manual**
```sql
-- Conectar ao MySQL como root
mysql -u root -p

-- Criar o banco de dados
CREATE DATABASE sistema_aluguel_carros;

-- Verificar se foi criado
SHOW DATABASES;
```

### 3. Configurar Credenciais

Edite o arquivo `src/main/resources/application.yml` se necessário:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sistema_aluguel_carros?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    username: root          # Seu usuário MySQL
    password: sua_senha     # Sua senha MySQL
```

### 4. Testar a Conexão

Execute a aplicação:
```bash
mvn spring-boot:run
```

Se tudo estiver correto, você verá:
- Mensagens de conexão com o banco
- Criação automática das tabelas
- Aplicação rodando na porta 8080

## Estrutura das Tabelas

As seguintes tabelas serão criadas automaticamente:

- `clientes` - Dados dos clientes
- `entidades_empregadoras` - Entidades empregadoras
- `rendimentos` - Rendimentos dos clientes

## Solução de Problemas

### Erro de Conexão
```
Access denied for user 'root'@'localhost'
```
**Solução:** Verifique usuário e senha no `application.yml`

### Erro de Driver
```
Could not create connection to database server
```
**Solução:** Verifique se o MySQL está rodando:
```bash
# Windows
net start mysql

# Linux/macOS
sudo systemctl start mysql
# ou
brew services start mysql
```

### Erro de Timezone
```
The server time zone value 'XXX' is unrecognized
```
**Solução:** Adicione `&serverTimezone=UTC` na URL de conexão (já incluído)

## Comandos Úteis

```sql
-- Conectar ao banco
mysql -u root -p

-- Ver bancos disponíveis
SHOW DATABASES;

-- Usar o banco do sistema
USE sistema_aluguel_carros;

-- Ver tabelas
SHOW TABLES;

-- Ver estrutura de uma tabela
DESCRIBE clientes;
```
