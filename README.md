# API de Gerenciamento de Tarefas com Notificações

API REST para gerenciamento de tarefas com sistema de notificações automáticas por e-mail, desenvolvida com Java e Spring Boot.

---

## Tecnologias

- **Java 25**
- **Spring Boot 4**
- **PostgreSQL** — banco de dados relacional
- **Docker Compose** — provisionamento do banco de dados
- **Spring Security + JWT** — autenticação stateless
- **Spring Mail** — envio de e-mails
- **Quartz Scheduler** — agendamento de jobs automáticos
- **Swagger / OpenAPI** — documentação interativa
- **Lombok** — redução de boilerplate
- **Bean Validation** — validação de entrada de dados

---

## Arquitetura

O projeto segue uma arquitetura em camadas com separação clara de responsabilidades:

```
Controller → Service → Repository → Entity
```

- **controller/** — recebe as requisições HTTP e delega ao service
- **service/** — contém a lógica de negócio
- **repository/** — interfaces JPA para acesso ao banco de dados
- **entity/** — entidades mapeadas para tabelas do PostgreSQL
- **dto/** — objetos de transferência de dados (entrada e saída da API)
- **security/** — configuração do Spring Security, filtro JWT e geração de tokens
- **exception/** — tratamento global de exceções
- **job/** — job do Quartz para notificações automáticas

---

## Funcionalidades

- Cadastro e autenticação de usuários com token JWT
- CRUD completo de tarefas com validação de dados
- Envio automático de e-mail ao criar uma tarefa
- Job agendado (a cada hora) que verifica tarefas próximas do vencimento e envia notificação por e-mail
- Documentação interativa via Swagger UI

---

## Como executar

### Pré-requisitos

- Java 25
- Docker Desktop

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/api.notification.git
cd api.notification
```

2. Execute a aplicação — o Docker Compose sobe o PostgreSQL automaticamente:
```bash
./mvnw spring-boot:run
```

3. Acesse a documentação:
```
http://localhost:8080/swagger-ui.html
```

---

## Endpoints

### Autenticação (público)

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/auth/register` | Cadastra um novo usuário e retorna token JWT |
| POST | `/auth/login` | Autentica o usuário e retorna token JWT |

### Tarefas (requer token JWT)

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/tasks` | Lista todas as tarefas |
| POST | `/tasks` | Cria uma nova tarefa |
| GET | `/tasks/{id}` | Busca uma tarefa por ID |
| PUT | `/tasks/{id}` | Atualiza uma tarefa |
| DELETE | `/tasks/{id}` | Remove uma tarefa |

### Usuários (requer token JWT)

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/users` | Lista todos os usuários |
| POST | `/users` | Cria um novo usuário |

---

## Autenticação

A API utiliza autenticação stateless com JWT. Para acessar rotas protegidas:

1. Cadastre-se em `POST /auth/register` ou faça login em `POST /auth/login`
2. Copie o token retornado
3. Envie o token no header de cada requisição:

```
Authorization: Bearer seu_token_aqui
```

No Swagger UI, clique em **Authorize** e cole o token no formato acima.

---

## Exemplo de uso

### Criar conta
```json
POST /auth/register
{
  "name": "Arthur",
  "email": "arthur@email.com",
  "password": "123456"
}
```

### Criar tarefa
```json
POST /tasks
{
  "title": "Estudar Spring Boot",
  "description": "Revisar conceitos de segurança",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2026-12-01T10:00:00",
  "userEmail": "arthur@email.com"
}
```

---

## Notificações automáticas

O Quartz Scheduler executa um job a cada hora que:

1. Busca todas as tarefas com `dueDate` nas próximas 24 horas e `notified = false`
2. Envia um e-mail de lembrete para o `userEmail` de cada tarefa
3. Marca a tarefa como `notified = true` para evitar envios duplicados

---

## Variáveis de configuração

| Propriedade | Descrição |
|-------------|-----------|
| `jwt.secret` | Chave secreta para assinar os tokens JWT |
| `jwt.expiration` | Tempo de expiração do token em milissegundos |
| `spring.mail.*` | Configurações do servidor SMTP |
| `spring.jpa.hibernate.ddl-auto` | Estratégia de criação das tabelas |
