document.addEventListener('DOMContentLoaded', function() {
    chechkAuthAndLoadData();

    const logoutButton = document.getElementById('logout-button');
    logoutButton.addEventListener('click', function() {
        localStorage.removeItem('authToken');
        alert('Você foi desconectado');
        window.location.href = 'LoginScreen.html';
    })
})

async function chechkAuthAndLoadData(params) {
    try {
        const userData = await fecthUserData();

        populateUserData(userData);
    } catch (error) {
        console.error('Erro na autenticação: ', error.message);
        alert('Sua sessão expirou ou é inválida. Por favor, faça login novamente.');

        window.location.href = 'LoginScreen.html';
    }
}


function populateUserData(userData) {
    document.getElementById('user-username').textContent = userData.username;
    document.getElementById('user-email').textContent = userData.email;
    document.getElementById('user-role').textContent = userData.role;
}