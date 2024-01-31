# Projeto CRUD com Java e JDBC

## Sobre o Projeto

Este projeto é um exemplo prático de um sistema CRUD (Create, Read, Update, Delete) desenvolvido em Java, utilizando JDBC para a conexão com o SGBD MySQL. O objetivo deste projeto é praticar a aplicação do padrão de projeto DAO (Data Access Object), que consiste em separar a lógica de negócios da lógica de acesso a dados em uma aplicação.

## Funcionalidades

- **Criação de registros**: Adiciona novos dados ao banco de dados.
- **Leitura de registros**: Consulta e exibe dados do banco de dados.
- **Atualização de registros**: Modifica dados existentes no banco de dados.
- **Exclusão de registros**: Remove dados do banco de dados.

## Estrutura do Projeto

- `application/`: Classes que rodam a aplicação.
- `db/`: Exceções e conexão com o banco de dados.
- `model/entities`: Classes que representam as entidades do banco de dados.
- `model/dao`: Contém as classes e interfaces DAO.
- `model/dao/impl`: Contém as classes que implementam as interfaces DAO.
- `sql/schema.sql`: Script para criação do banco de dados.

## Como Executar

Para executar este projeto, você precisará ter instalado em sua máquina:
- Java JDK 21
- MySQL 8.0

### Passos para Execução

1. **Clone o Repositório**: git clone https://github.com/isaquearauj/dao-jdbc.git
2. **Importe o Projeto**: Abra o projeto em sua IDE de preferência (recomendado: Eclipse, IntelliJ IDEA).
3. **Configuração do Banco de Dados**:
- Certifique-se de que o MySQL está instalado e em execução em sua máquina.
- Crie um banco de dados MySQL utilizando o script SQL fornecido no diretório `sql/`.
4. **Configurar a Conexão**:
- Altere a classe `src/db/DB` com suas credenciais do MySQL.
5. **Execute o Projeto**:
- Execute os arquivos `src/application/...Program` para rodar a aplicação.

## Contato

[linkedin](https://www.linkedin.com/in/isaquearauj/) ||
📧: isaqueaj11@gmail.com
