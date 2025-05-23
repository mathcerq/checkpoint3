
# 🗓️ Checkpoint 3 - Sistema de Agenda de Consultas

Este projeto é um sistema de agenda de consultas desenvolvido em Java utilizando Spring Boot. Ele permite o cadastro e gerenciamento de pacientes, profissionais e consultas, além de disponibilizar consultas específicas por data e status.

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3.4.6
- Spring Web
- Spring Data JPA
- H2 Database (banco em memória)
- Swagger / OpenAPI (Documentação da API)
- Maven

## 💻 Funcionalidades

- CRUD de Pacientes
- CRUD de Profissionais
- CRUD de Consultas
- Consultas específicas:
  - Estatísticas de consultas realizadas por profissional
  - Consultas de um paciente filtrando por status e período
  - Consultas de um profissional filtrando por status e período
  - Consultas gerais filtrando por status e período

## 🔗 Documentação Swagger

Acesse a documentação da API:  
➡️ **http://localhost:8080/swagger-ui/index.html**

## 💾 Banco de dados H2

Acesse o console do H2:  
➡️ **http://localhost:8080/h2-console**

**Configurações de conexão:**  
- JDBC URL: `jdbc:h2:mem:db`  
- User: `sa`  
- Password: (vazio)

## 📦 Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/mathcerq/checkpoint3.git
```

2. Abra o projeto no Eclipse ou na sua IDE preferida.

3. Execute a classe:  
➡️ `Checkpoint3Application.java`

4. Acesse:  
- Swagger: **http://localhost:8080/swagger-ui/index.html**  
- H2 Console: **http://localhost:8080/h2-console**

## 🗺️ Estrutura de pacotes

```
src/main/java/br/com/fiap/checkpoint3
├── controller    # Controllers REST
├── model         # Entidades JPA
├── repository    # Interfaces de acesso a dados
├── Checkpoint3Application.java  # Classe principal
```

## 👨‍💻 Desenvolvido por

- Matheus Cerqueira - [GitHub](https://github.com/mathcerq)
