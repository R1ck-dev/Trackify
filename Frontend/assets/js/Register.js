//Espera todo o HTML carregar antes de rodar o script
document.addEventListener('DOMContentLoaded', function() {
    const registerForm = document.getElementById('register-form');

    registerForm.addEventListener('submit',   async function(event) {

        //Impede o comportamente padrão do formulário de recarregar a página 
        event.preventDefault();

        const username = document.getElementById('register__username-user').value;
        const email = document.getElementById('register__email-user').value;
        const password = document.getElementById('register__password-user').value;
        const confirmPassword = document.getElementById('register__confirm-password-user').value;

        if (password != confirmPassword) {
            alert('As senhas não conferem. Por favor, digite novamente.');
            return;
        }

        try {
            const newUser = await registerUser(username, email, password);

            console.log('Usuário cadastrado:', newUser);
            alert('Usuário cadastrado com sucesso!');
            
            window.location.href = 'LoginScreen.html';
        }
        catch (error) {
            //Erros de rede
            console.error('Erro de rede ao tentar cadastrar: ', error);
            alert('Não foi possível conectar ao servidor.')
        }
    })
})