# Desabafa-dev: Gaming MS

O **Gaming MS** é o microservice responsável pela **engine de gamificação** e pelo **gerenciamento de perfis** do Desabafa. Utilizando **Spring Boot** e persistindo dados em um banco PostgreSQL hospedado no Supabase, este serviço oferece uma experiência lúdica e motivadora para a participação em reuniões.

---

## 🚀 Features Principais

1. **Engine de Gamificação**
   Nosso motor de cálculo aplica fórmulas matemáticas para gerenciar o XP do usuário baseado em múltiplos fatores:

    * Reuniões confirmadas ou recusadas
    * Penalidades por reuniões improdutivas
    * Bônus ao completar *quests* como o modo treino e o *Desabafar*\\

2. **Gerenciamento de Perfil de Usuário**

    * Perfil definido pelo UUID fornecido pelo OAuth do Google.
    * Atributos: nome, sobrenome, e-mail, cargo, descrição do cargo.
    * Reuniões e evolução do jogador.

3. **Sincronização de Reuniões**

    * Consumo de API externa (`RestClient`) para ler eventos do Google Calendar.
    * Mapeamento DTO ↔ entidade JPA (`Meeting`) para persistência.

---

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot 3.x**
* **PostgreSQL** via **Supabase**
* **Gradle**

---

## ⚙️ Pré-requisitos

* JDK 21
* Gradle 8.x (wrapper incluso)
* Conta e projeto Supabase com banco PostgreSQL

---

## 🔧 Configuração do Projeto

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu-usuario/desabafa.git
   cd desabafa
   ```
2. **Configure o *`application.yml`**:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://<host_supabase>:5432/postgres?
       username: postgres
       password: <your-supabase-password>
     jpa:
       hibernate:
         ddl-auto: update
       properties:
         hibernate.dialect: org.hibernate.dialect.PostgreSQLDialet
   ```

---

## 🏃‍♂️ Executando Localmente

* **Via Gradle**:

  ```bash
  ./gradlew bootRun
  ```

  &#x20;


###

---

## 📑 Endpoints REST

### Perfil de Usuário

| Método | URL                     | Descrição                                  |
|--------|-------------------------|--------------------------------------------|
| GET    | `/api/profile/{userId}` | Retorna o `UserDto` do usuário             |
| POST   | `/api/profile`          | Cria um novo perfil baseado no `UserDto`   |
| PUT    | `/api/profile/{userId}` | Atualiza um perfil existente               |

### Endpoints

#### 1. Obter Perfil do Usuário

*   **Método HTTP:** `GET`
*   **Caminho:** `/{userId}`
*   **Descrição:** Recupera as informações de perfil de um usuário específico.
*   **Variáveis de Caminho:**
    *   `userId` (String): O identificador único do usuário.
*   **Respostas:**
    *   `200 OK`: Retorna o objeto `UserDto` contendo as informações de perfil do usuário.

#### 2. Salvar Perfil do Usuário

*   **Método HTTP:** `POST`
*   **Caminho:** `/`
*   **Descrição:** Cria e salva um novo perfil de usuário.
*   **Corpo da Requisição:** `UserDto` - Um objeto JSON representando os dados do perfil do usuário.
    ```json
    {
      "userId": "string (formato UUID)",
      "name": "string",
      "lastName": "string",
      "email": "string (formato email)",
      "jobTitle": "string",
      "jobDescription": "string",
      "totalXp": "integer (opcional, padrão 0)",
      "level": "integer (opcional, padrão 1)",
      "questXp": "integer (opcional, padrão 0)"
    }
    ```
*   **Respostas:**
    *   `200 OK`: Retorna o objeto `UserDto` que foi salvo.

#### 3. Atualizar Perfil do Usuário

*   **Método HTTP:** `PUT`
*   **Caminho:** `/{userId}`
*   **Descrição:** Atualiza as informações de perfil de um usuário existente. O `userId` no caminho é usado para identificar o usuário a ser atualizado, enquanto o `userId` no corpo da requisição `UserDto` é ignorado para a operação de atualização em si, mas deve corresponder ao usuário que está sendo atualizado.
*   **Variáveis de Caminho:**
    *   `userId` (String): O identificador único do usuário cujo perfil deve ser atualizado.
*   **Corpo da Requisição:** `UserDto` - Um objeto JSON contendo os dados do perfil atualizados. Campos não fornecidos ou nulos serão ignorados (exceto para tipos primitivos que assumirão valores padrão se não definidos no DTO).
    ```json
    {
      "userId": "string (formato UUID, deve corresponder à variável de caminho)",
      "name": "string",
      "lastName": "string",
      "email": "string (formato email)",
      "jobTitle": "string",
      "jobDescription": "string",
      "totalXp": "integer",
      "level": "integer",
      "questXp": "integer"
    }
    ```
*   **Respostas:**
    *   `200 OK`: Retorna o objeto `UserDto` refletindo o estado antes da atualização ser aplicada ao banco de dados, mas após o DTO de entrada ser processado pelo serviço.

## Controller de Gamificação

O `GamingController` lida com operações relacionadas às estatísticas de jogo do usuário, como pontos de experiência (XP).

**Caminho Base:** `/api/gaming`

### Endpoints

#### 1. Aumentar XP de Missão

*   **Método HTTP:** `POST`
*   **Caminho:** `/{userId}`
*   **Descrição:** Aumenta os pontos do questXP para um usuário específico. Este endpoint normalmente aciona uma chamada de serviço que incrementa o `questXp` do usuário e potencialmente atualiza seu nível com base no XP total.
*   **Variáveis de Caminho:**
    *   `userId` (String): O identificador único do usuário.
*   **Respostas:**
    *   `200 OK`: Indica que o XP da missão foi processado com sucesso para um aumento.