const API_BASE_URL = 'http://localhost:8080';

async function registerUser(username, email, password) {
    const userData = {
        username: username,
        email: email,
        password: password
    };

    const response = await fetch(`${API_BASE_URL}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    });

    if (!response.ok) {
        throw new Error('Falha no cadastro. Verifique os dados ou tente mais tarde.');
    }

    const registeredUser = await response.json();

    return registeredUser;
}

async function loginUser(email, password) {
    const userData = {
        email: email,
        password: password
    };

    const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    });

    if (!response.ok) {
        throw new Error('Falha no login. Verifique email ou senha.')
    }

    const data = await response.json()

    return data.token;
}

async function fetchUserData() {
    const token = localStorage.getItem('authToken');

    if (!token) {
        throw new Error('Usuário não autenticado. Token não encontrado.');
    }

    const response = await fetch(`${API_BASE_URL}/users/me`, {
        method: 'GET',

        headers: {
            'Content-Type': 'application/json',

            'Authorization': 'Bearer ' + token
        }
    });

    if (!response.ok) {
        console.error('Falha ao buscar dados de usuário. Status: ', response.status);
        throw new Error('Falha na autenticação. Por favor, faça login novamente.');
    }

    const userData = await response.json();
    return userData;
}

async function createMediaEntry(mediaData) {
    const token = localStorage.getItem('authToken');

    if (!token) {
        throw new Error('Usuário não autenticado. Token não encontrado.');
    }

    const response = await fetch(`${API_BASE_URL}/media`, {
        method: 'POST',

        headers: {
            'Content-Type': 'application/json',

            'Authorization': 'Bearer ' + token
        },

        body: JSON.stringify(mediaData)
    });

    if (!response.ok) {
        console.error('Falha ao criar mídia. Status:', response.status);
        throw new Error('Falha ao salvar a mídia. Verifique os dados.');
    }

    const createdMedia = await response.json();
    return createdMedia;
}