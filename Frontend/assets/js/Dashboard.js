document.addEventListener('DOMContentLoaded', function() {
    checkAuthAndLoadData();

    const logoutButton = document.getElementById('logout-button');
    logoutButton.addEventListener('click', function() {
        localStorage.removeItem('authToken');
        alert('Você foi desconectado');
        window.location.href = 'LoginScreen.html';
    });

    const createMediaForm = document.getElementById('create-media')

    createMediaForm.addEventListener('submit', async function(event) {
        
        event.preventDefault();

        try {
            const title = document.getElementById('create-media__title').value;
            const author = document.getElementById('create-media__author').value;
            const type = document.getElementById('create-media__type').value;
            const status = document.getElementById('create-media__status').value;
            const coverImageUrl = document.getElementById('create-media__cover-image').value;
            const description = document.getElementById('create-media__description').value;
            const ratingInput = document.getElementById('create-media__rating').value;
            const personalNotes = document.getElementById('create-media__personal-notes').value;

            const rating = ratingInput ? parseInt(ratingInput, 10) : null;

            const mediaData = {
                title: title,
                author: author,
                type: type,
                status: status,
                coverImageUrl: coverImageUrl || null,
                description: description || null,
                rating: rating,
                personalNotes: personalNotes || null
            };

            const newMedia = await createMediaEntry(mediaData);

            alert(`Mídia "${newMedia.title}" salva com sucesso!`);

            createMediaForm.reset();
        } catch (error) {
            console.error('Erro ao salvar mídia: ', error.message);
            alert(error.message);
        }
    })
})

async function checkAuthAndLoadData(params) {
    try {
        const userData = await fetchUserData();

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