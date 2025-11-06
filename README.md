# 📍 Trackify - Gerenciador de Mídias

Um gerenciador de mídias (livros, mangás, etc.) full-stack. Este projeto foi construído para demonstrar um fluxo C.R.U.D. (Create, Read, Update, Delete) completo, com autenticação JWT segura, conectando um backend Java Spring Boot a um frontend em JavaScript Vanilla.

---

## ✨ Funcionalidades (Features)

* **Autenticação de Usuário:** Sistema completo de Registro e Login com Tokens JWT (JSON Web Tokens).
* **Segurança:** Senhas criptografadas (BCrypt), rotas de API protegidas e verificação de "posse" (usuário só pode alterar os próprios dados).
* **Biblioteca Pessoal (CRUD):**
    * **Create:** Adicionar novas mídias ao catálogo global e à biblioteca pessoal.
    * **Read:** Carregar e exibir a biblioteca de mídias do usuário.
    * **Update:** Editar o status, nota e notas pessoais (via um modal).
    * **Delete:** Excluir mídias da biblioteca pessoal (com confirmação).
* **Frontend Reativo:** A interface do usuário (UI) é atualizada dinamicamente (sem recarregar a página) ao criar, editar ou deletar mídias, usando Manipulação de DOM.

---

## 🚀 Tecnologias Utilizadas

O projeto é dividido em duas partes: `backend` e `frontend`.

### Backend (API REST)
* **Java 21**
* **Spring Boot 3**
* **Spring Security:** Para autenticação JWT e segurança de endpoints.
* **Spring Data JPA:** Para persistência de dados.
* **MySQL:** Banco de dados relacional.
* **Lombok:** Para redução de boilerplate.
* **Maven:** Gerenciador de dependências.

### Frontend (SPA)
* **HTML5** (Semântico)
* **CSS3:**
    * Flexbox e CSS Grid para layout.
    * Variáveis CSS (`:root`) e Dark Mode.
    * Animações (`@keyframes`) e transições.
* **JavaScript (ES6+ Vanilla):**
    * `fetch` API para consumo da API REST.
    * `async/await` para programação assíncrona.
    * Manipulação de DOM (Criação de cards, modais, etc.).
    * Delegação de Eventos (`data-*` attributes).

---

## 🛠️ Como Rodar Localmente

### Pré-requisitos
* Java 17+
* Maven
* MySQL (ou um SGBD compatível)
* VS Code com a extensão "Live Server" (para o frontend)

### 1. Backend
```bash
# 1. Clone o repositório
git clone [https://github.com/R1ck-dev/trackify.git](https://github.com/R1ck-dev/trackify.git)

# 2. Navegue até a pasta do backend

# 3. Crie o arquivo de segredos
# (Dentro de /src/main/resources/)
# Crie o arquivo: application-local.properties
# E adicione suas credenciais:
spring.datasource.password=SUA_SENHA_DO_MYSQL
jwt.secret=SEU_SEGREDO_JWT_LONGO

# 4. Rode o projeto
./mvnw spring-boot:run