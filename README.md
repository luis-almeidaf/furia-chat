# Furia Challenge 1: Experiência Conversacional

## Descrição

Este projeto é uma aplicação web que permite a interação entre torcedores da FURIA, com um chat em tempo real e informações sobre a equipe de CS:GO.

## Objetivo do Projeto

Desenvolvido como parte do desafio para a vaga de **Assistente de Engenharia de Software**.

## Acesso ao Projeto

- **Frontend**: [furia-chat-theta.vercel.app](https://furia-chat-theta.vercel.app)
- **Backend (API)**: [furia-chat-production.up.railway.app](https://furia-chat-production.up.railway.app)

## Tecnologias Utilizadas

### Backend

- Java 21
- Spring Boot 3.4.5
- Deploy: [Railway](https://railway.app)

### Frontend

- Angular 19
- Deploy: [Vercel](https://vercel.com)

### Banco de Dados

- PostgreSQL 16 (ambiente de desenvolvimento com Docker)
- Produção: [Neon](https://neon.tech)

## Como Rodar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/luis-almeidaf/furia-chat.git
cd furia-chat
```

### 2. Rodar o Backend

```bash
cd backend
```

Crie um arquivo `.env` com as variáveis de ambiente:

```dotenv
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_USER=local_user
POSTGRES_DB=local_db
POSTGRES_PASSWORD=local_password
```

Inicie o container do PostgreSQL:

```bash
docker-compose up -d
```

> 💡 Certifique-se de que o Docker Desktop esteja em execução.

Inicie a aplicação Spring Boot:

- **Windows**:

  ```bash
  .\mvnw.cmd spring-boot:run
  ```

- **Linux/macOS**:

  ```bash
  ./mvnw spring-boot:run
  ```

> ℹ️ O projeto utiliza **Flyway** para controle de versão do banco de dados. As migrações são aplicadas automaticamente ao iniciar a aplicação.

---

### 3. Rodar o Frontend

Volte para a raiz do projeto:

```bash
cd ..
```

Acesse a pasta do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Inicie a aplicação Angular:

```bash
ng serve --open
```

A aplicação estará disponível em: [http://localhost:4200](http://localhost:4200)

## 📡 API - Endpoints

### Endpoint `/players`

#### 🔹 `GET /players`

Retorna uma lista paginada de jogadores.

**Parâmetros de query (opcional):**

- `page`: número da página (ex: `0`)
- `size`: quantidade por página (ex: `10`)
- `sort`: campo de ordenação (ex: `role,desc`)

**Exemplo**: `/players?page=0&size=10&sort=role,desc`

**Resposta**: `200 OK`

**Resposta de exemplo:**

```json
{
  "content": [
    {
      "id": 1,
      "nickname": "Yekindar",
      "role": "Rifler",
      "img": "https://raw.githubusercontent.com/luis-almeidaf/image-hosting/refs/heads/main/yekindar-.png",
      "createdAt": "2025-05-01T21:21:13.482496",
      "updatedAt": "2025-05-01T21:21:13.482496"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 5,
  "totalPages": 1,
  "first": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 5,
  "empty": false
}
```

---

#### 🔹 `GET /players/{id}`

Retorna os dados de um jogador específico.

**Parâmetros:**

- `id`: ID do jogador (ex: `4`)

**Resposta**: `200 OK`

**Resposta de exemplo:**

```json
{
  "id": 4,
  "nickname": "yuurih",
  "role": "Rifler",
  "img": "https://raw.githubusercontent.com/luis-almeidaf/image-hosting/refs/heads/main/yuurih.png",
  "createdAt": "2025-05-01T21:21:13.482496",
  "updatedAt": "2025-05-01T21:21:13.482496"
}
```

**Erro**: `404 Not Found` (caso o jogador não exista)

---

#### 🔹 `POST /players`

Cria um novo jogador.

**Corpo da requisição:**

```json
{
  "nickname": "Yuurih",
  "role": "Rifler",
  "img": "https://raw.githubusercontent.com/luis-almeidaf/image-hosting/refs/heads/main/yuurih.png"
}
```

**Resposta**: `201 Created`

**Resposta de exemplo:**

```json
{
  "id": 6,
  "nickname": "Yuurih",
  "role": "Rifler",
  "img": "https://raw.githubusercontent.com/luis-almeidaf/image-hosting/refs/heads/main/yuurih.png",
  "createdAt": "2025-05-04T19:49:09.3734812",
  "updatedAt": "2025-05-04T19:49:09.3734812"
}
```

---

#### 🔹 `PUT /players/{id}`

Atualiza os dados de um jogador existente.

**Parâmetros:**

- `id`: ID do jogador (ex: `6`)

**Corpo da requisição:**

```json
{
  "nickname": "Kscerato",
  "role": "Rifler",
  "img": "https://raw.githubusercontent.com/luis-almeidaf/image-hosting/refs/heads/main/kscerato-.png"
}
```

**Resposta**: `200 OK`

**Resposta de exemplo:**

```json
{
  "id": 6,
  "nickname": "Kscerato",
  "role": "Rifler",
  "img": "https://raw.githubusercontent.com/luis-almeidaf/image-hosting/refs/heads/main/kscerato-.png",
  "createdAt": "2025-05-04T19:50:37.0377409",
  "updatedAt": "2025-05-04T19:50:37.0377409"
}
```

**Erro**: `404 Not Found` (caso o jogador não exista)

---

#### 🔹 `DELETE /players/{id}`

Remove um jogador pelo ID.

**Parâmetros:**

- `id`: ID do jogador (ex: `6`)

**Resposta**: `204 No Content`

**Erro**: `404 Not Found` (caso o jogador não exista)

---

### Endpoint `/matches`

#### 🔹 `GET /matches`

Retorna uma lista paginada de partidas.

**Parâmetros de query (opcional):**

- `page`: número da página (ex: `0`)
- `size`: quantidade por página (ex: `10`)
- `sort`: campo de ordenação (ex: `opponent,desc`)

**Exemplo**: `/matches?page=0&size=10&sort=opponent,desc`

**Resposta**: `200 OK`

**Resposta de exemplo:**

```json
{
  "content": [
    {
      "id": 1,
      "opponent": "The Mongolz",
      "gameDate": "2025-05-10T05:00:00",
      "result": "",
      "createdAt": "2025-05-03T22:02:34.483373",
      "updatedAt": "2025-05-03T22:02:34.483373"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 2,
  "totalPages": 1,
  "first": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 2,
  "empty": false
}
```

---

#### 🔹 `GET /matches/{id}`

Retorna os detalhes de uma partida específica.

**Parâmetros:**

- `id`: ID da partida (ex: `2`)

**Resposta**: `200 OK`

**Resposta de exemplo:**

```json
{
  "id": 2,
  "opponent": "The Mongolz",
  "gameDate": "2025-05-10T05:00:00",
  "result": "",
  "createdAt": "2025-05-03T22:02:34.483373",
  "updatedAt": "2025-05-03T22:02:34.483373"
}
```

**Erro**: `404 Not Found` (caso a partida não exista)

---

#### 🔹 `POST /matches`

Cria uma nova partida.

**Corpo da requisição:**

```json
{
  "opponent": "Teste",
  "gameDate": "2025-05-10T05:00:00",
  "result": ""
}
```

**Resposta**: `201 Created`

**Resposta de exemplo:**

```json
{
  "id": 3,
  "opponent": "Teste",
  "gameDate": "2025-05-10T05:00:00",
  "result": "",
  "createdAt": "2025-05-04T19:38:54.5215271",
  "updatedAt": "2025-05-04T19:38:54.5215271"
}
```

**Erro**: `500 Internal Server Error` (caso ocorra um erro no servidor)

---

#### 🔹 `PUT /matches/{id}`

Atualiza os dados de uma partida existente.

**Parâmetros:**

- `id`: ID da partida (ex: `2`)

**Corpo da requisição:**

```json
{
  "opponent": "The Mongolz",
  "gameDate": "2025-05-10T05:00:00",
  "result": "2-0"
}
```

**Resposta**: `200 OK`

**Resposta de exemplo:**

```json
{
  "id": 2,
  "opponent": "The Mongolz",
  "gameDate": "2025-05-10T05:00:00",
  "result": "2-0",
  "createdAt": "2025-05-04T19:56:28.9093964",
  "updatedAt": "2025-05-04T19:56:28.9093964"
}
```

**Erro**: `404 Not Found` (caso a partida não exista)

---

#### 🔹 `DELETE /matches/{id}`

Remove uma partida pelo ID.

**Parâmetros:**

- `id`: ID da partida (ex: `2`)

**Resposta**: `204 No Content`

**Erro**: `404 Not Found` (caso a partida não exista)

---

### WebSocket `/livechat`

#### `/ws/new-message` (via WebSocket)

Envia uma nova mensagem para o chat ao vivo.

**Canal**: `/app/new-message`

**Broadcast**: `/topics/livechat`

**Payload:**

```json
{
  "user": "Luis",
  "message": "Vamos FURIA!"
}
```

**Resposta** (broadcast para todos os inscritos):

```json
{
  "output": "Luis: Vamos FURIA!"
}
```

## Status do Projeto

✅ Projeto **finalizado para entrega do desafio**.

Melhorias e novas funcionalidades podem ser adicionadas futuramente.

## Contato

Caso queira entrar em contato comigo:

- **E-mail:** [luis.almeida.faria@gmail.com](mailto:luis.almeida.faria@gmail.com)
- **LinkedIn:** [linkedin.com/in/luis-almeidaf](https://www.linkedin.com/in/luis-almeidaf)

---
