# vacancy-management

API Spring Boot simples para cadastro de candidato com validacao de payload.

## Estado atual

Este repositorio nao implementa persistencia em banco ainda. No estado atual ele expoe apenas um endpoint HTTP que valida os dados recebidos e responde:

- `200 OK` para payload valido
- `400 Bad Request` para payload invalido

## Requisitos

- Java 17 ou superior

## Como executar

```bash
./mvnw spring-boot:run
```

A aplicacao sobe por padrao em `http://localhost:8080`.

Se a porta `8080` ja estiver em uso, rode:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments='--server.port=8086'
```

## Como testar

```bash
./mvnw verify
```

## Endpoint

### `POST /candidate/`

Exemplo valido:

```bash
curl -i -X POST http://localhost:8080/candidate/ \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Gustavo",
    "username": "gustavo",
    "email": "gustavo@example.com",
    "password": "1234567890",
    "description": "dev",
    "curriculum": "cv"
  }'
```

Resposta esperada:

```http
HTTP/1.1 200 OK
```

Exemplo invalido:

```bash
curl -i -X POST http://localhost:8080/candidate/ \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Gustavo",
    "username": "gus tavo",
    "email": "email-invalido",
    "password": "123",
    "description": "dev",
    "curriculum": "cv"
  }'
```

Exemplo de resposta (a ordem dos erros pode variar):

```json
[
  {
    "message": "O campo [username] nao deve conter espaco",
    "field": "username"
  },
  {
    "message": "O campo [email] deve conter um e-mail valido",
    "field": "email"
  },
  {
    "message": "length must be between 10 and 100",
    "field": "password"
  }
]
```
