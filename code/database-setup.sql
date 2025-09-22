-- Script para configuração inicial do banco de dados MySQL
-- Execute este script no MySQL para criar o banco de dados

-- Criar o banco de dados (opcional - a aplicação pode criar automaticamente)
CREATE DATABASE IF NOT EXISTS sistema_aluguel_carros;

-- Usar o banco de dados
USE sistema_aluguel_carros;

-- Verificar se o banco foi criado
SHOW DATABASES;

-- As tabelas serão criadas automaticamente pela aplicação Spring Boot
-- baseadas nas entidades JPA quando a aplicação for executada
