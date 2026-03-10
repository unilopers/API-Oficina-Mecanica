# API Oficina Mecânica
API REST desenvolvida em Java com Spring Boot para gerenciamento de uma oficina mecânica.
Autenticação via Bearer Token (JWT).

## Tecnologias
- Java 21
- Spring Boot
- Spring Security
- JWT (JJWT)
- JPA / Hibernate
- BCrypt

## Como rodar o projeto
### Pré-requisitos
- Java 21 instalado
- Maven instalado
- Banco de dados configurado (MySQL / PostgreSQL / H2)

### Variáveis de ambiente
Crie um arquivo .env ou configure as variáveis:
URL_DB=jdbc:mysql://localhost:3306/oficina
USER_DB=root
SENHA_DB=suasenha
JWT_SECRET=sua_chave_secreta_aqui
JWT_EXPIRATION=86400000

### Rodando
mvn spring-boot:run

## Endpoints

### POST /auth/register
Registra um novo usuário.
Body: { "nome": "João", "email": "joao@email.com", "senha": "123456" }
Resposta: 201 Created

### POST /auth/login
Autentica o usuário e retorna o token JWT.
Body: { "email": "joao@email.com", "senha": "123456" }
Resposta: 200 OK + { "token": "...", "tipo": "Bearer", ... }

### GET /auth/profile  [PROTEGIDO]
Retorna os dados do usuário autenticado.
Header: Authorization: Bearer {token}
Resposta: 200 OK + { "id": 1, "nome": "João", "email": "..." }

## Como autenticar
1. Faça POST /auth/login e copie o token da resposta
2. Em rotas protegidas, adicione o header:
   Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

## Documentação interativa (Swagger)
Com a API rodando, acesse: http://localhost:8080/swagger-ui.html
