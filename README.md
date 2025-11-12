# 📍 Trackify - Gerenciador de Mídias

Um gerenciador de mídias (livros, mangás, etc.) full-stack. Este projeto foi construído para demonstrar um fluxo C.R.U.D. (Create, Read, Update, Delete) completo, com autenticação JWT segura, conectando um backend Java Spring Boot a um frontend em JavaScript Vanilla.

---

### 🌐 Aplicação Ao Vivo
* **Frontend (Netlify):** **[https://trackify-midia.netlify.app/](https://trackify-midia.netlify.app/)**
* **Backend (Render):** **`https://trackify-backend-4d5f.onrender.com`**

*(Nota: O backend hospedado no plano gratuito do Render pode "dormir" após 15 minutos de inatividade. O primeiro login pode levar até 60 segundos para "acordar" o servidor.)*

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

## 🛠️ Como Rodar Localmente (Docker)

Este projeto é 100% "containerizado". A forma mais fácil de rodá-lo é com o Docker, pois ele configura o frontend, o backend e o banco de dados de uma só vez.

### Pré-requisitos
* **Git**
* **Docker** e **Docker Compose**

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/R1ck-dev/Trackify.git](https://github.com/R1ck-dev/Trackify.git)
    cd Trackify
    ```

2.  **Crie o arquivo de segredos do Docker:**
    O `.gitignore` ignora os segredos. Você precisa criá-lo manualmente.

    * Crie o arquivo: `Backend/trackify/src/main/resources/application-docker.properties`
    * Cole o seguinte conteúdo nele:

    ```properties
    # Diz ao Spring para criar/atualizar as tabelas no banco de dados do Docker
    spring.jpa.hibernate.ddl-auto=update

    # Adicione seu próprio segredo JWT longo e seguro aqui
    jwt.secret=SEGREDO_JWT
    ```

3.  **Construir a imagem:**
    Este comando irá construir as imagens do backend e frontend, baixar o MySQL e ligar os três contêineres juntos.

    ```bash
    sudo docker compose up --build
    ```

4.  **Acesse o App!**
    * Frontend: **`http://localhost:5500`**
    * Backend API: `http://localhost:8080`
    * Banco de Dados (via Workbench): `localhost:3306` (usuário: `admin`, senha: `admin`)