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
            loadAndDisplayMedia();

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
        await loadAndDisplayMedia();

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

async function loadAndDisplayMedia() {
    try {
        const mediaList = await fetchMediaData();
        populateMediaList(mediaList);
    } catch (error) {
        console.error('Erro ao carregar lista de mídias:', error.message);
        alert('Não foi possível carregar sua biblioteca de mídias.');
    }
}

function populateMediaList(mediaList) {
    const listContainer = document.getElementById('media-list');
    const placeholder = document.getElementById('media-list-placeholder');

    listContainer.innerHTML = '';

    if (mediaList.length === 0) {
        placeholder.textContent = 'Sua biblioteca está vazia. Adicione sua primeira mídia no formulário acima!';
        listContainer.appendChild(placeholder);
        return;
    }

    mediaList.forEach(media => {
        const mediaCard = document.createElement('div');

        mediaCard.className = 'media-card';

        mediaCard.innerHTML = `<div class="media-card">
                                    <img src="${media.coverImageUrl || 'assets/images/placeholder.png'}" alt="Capa">
                                    <div class="media-card-content">
                                        <h3>${media.title}</h3>
                                        <p>${media.author}</p>
                                        <p>Status: ${media.status}</p>
                                        <p>Nota: ${media.rating}</p>
                                    </div>
                                    </div>
                                    `;

            listContainer.appendChild(mediaCard);
    });
}