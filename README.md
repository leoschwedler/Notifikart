# 🛒 Notifikart - E-commerce Simplificado com Spring Boot e Kafka

O **Notifikart** é um projeto de e-commerce simplificado, desenvolvido com **Spring Boot**, **Kafka** e **MongoDB**, com autenticação segura via **Keycloak**.  
Ele demonstra o fluxo completo de **criação de pedidos**, **publicação de eventos Kafka** e **notificação assíncrona** entre microsserviços.

---

## 🧱 Arquitetura

O sistema é composto por dois microsserviços principais:

### 🧩 `order-service`
- Responsável por **gerenciar pedidos** e **enviar mensagens** para o Kafka.  
- Quando um pedido é criado, ele publica um evento no tópico `notification-topic`.

### ✉️ `notification-service`
- **Consome mensagens** do tópico Kafka.
- Desserializa o JSON recebido e processa a notificação (por exemplo, envio de e-mail, push ou log).

### 🔐 `keycloak`
- Servidor de autorização (**Authorization Server**).
- Responsável por emitir e validar tokens JWT usados na autenticação.

### ⚙️ `kafka`
- Responsável pela **mensageria assíncrona** entre os microsserviços.

---

## 📦 Tecnologias Utilizadas

| Tecnologia | Função |
|-------------|--------|
| **Java 17** | Linguagem principal |
| **Spring Boot 3.3.4** | Framework principal para os microsserviços |
| Spring Web | Criação de APIs REST |
| Spring Security (OAuth2 Resource Server) | Autenticação e autorização via JWT |
| Spring Kafka | Integração com Apache Kafka |
| Spring Data MongoDB | Persistência de dados |
| Spring State Machine | Controle de estados do pedido |
| Lombok | Redução de código boilerplate |
| **Apache Kafka** | Mensageria entre microsserviços |
| **MongoDB** | Banco de dados NoSQL |
| **Keycloak** | Authorization Server (geração e validação de tokens JWT) |

---

## 🚀 Fluxo de Funcionamento

1. O **cliente** cria um novo pedido via `order-service`.
2. O `order-service` **envia uma mensagem** para o Kafka (tópico `notification-topic`).
3. O `notification-service` **consome** essa mensagem e processa a notificação.

---

## 🧾 Exemplo de Payload

Este é um exemplo de mensagem JSON que representa um pedido enviado para o Kafka:

```json
{
  "customerId": "customer123",
  "basketId": "basket456",
  "amount": 25.00,
  "shippingCost": 10.00
}
