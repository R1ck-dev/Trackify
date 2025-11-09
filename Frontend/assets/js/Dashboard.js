document.addEventListener('DOMContentLoaded', function() {
    
    checkAuthAndLoadData();

    const logoutButton = document.getElementById('logout-button');
    logoutButton.addEventListener('click', function() {
        localStorage.removeItem('authToken');
        alert('Você foi desconectado');
        window.location.href = 'index.html';
    });

    const createMediaForm = document.getElementById('create-media');
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
                title: title, author: author, type: type, status: status,
                coverImageUrl: coverImageUrl || null, description: description || null,
                rating: rating, personalNotes: personalNotes || null
            };
            const newMedia = await createMediaEntry(mediaData);
            alert(`Mídia "${newMedia.title}" salva com sucesso!`);
            createMediaForm.reset();
            loadAndDisplayMedia();
        } catch (error) {
            console.error('Erro ao salvar mídia: ', error.message);
            alert(error.message);
        }
    });

    const editModal = document.getElementById('edit-modal');
    const closeModalBtn = document.getElementById('modal-close-btn');

    closeModalBtn.addEventListener('click', function() {
        editModal.classList.remove('is-active');
    });
    
    editModal.addEventListener('click', function(event) {
        if (event.target === editModal) {
            editModal.classList.remove('is-active');
        }
    });

    const mediaListContainer = document.getElementById('media-list');
    mediaListContainer.addEventListener('click', function(event) {
        
        const clickedElement = event.target;
        
        const deleteButton = clickedElement.closest('.media-card-delete-btn');
        if (deleteButton) {
            const mediaId = deleteButton.dataset.mediaId;
            handleDeleteMedia(mediaId, deleteButton);
            return; 
        }

        const editButton = clickedElement.closest('.media-card-edit-btn');
        if (editButton) {

            openEditModal(editButton);
        }
    });

    const editForm = document.getElementById('edit-form');

    editForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        try {
            const mediaId = document.getElementById('edit-media-id').value;
            const status = document.getElementById('edit-media-status').value;
            const ratingInput = document.getElementById('edit-media-rating').value;
            const personalNotes = document.getElementById('edit-media-notes').value;

            const updatedData = {
                status: status,
                rating: ratingInput ? parseInt(ratingInput, 10) : null,
                personalNotes: personalNotes || null
            };

            const updatedMedia = await updateMediaEntry(mediaId, updatedData);

            alert('Mídia atualizada com sucesso!');

            editModal.classList.remove('is-active');

            await loadAndDisplayMedia();
        } catch (error) {
            console.error('Erro ao atualizar mídia:', error.message);
            alert(error.message);
        }
    });
}); // FIM DO DOMCONTENTLOADED

async function checkAuthAndLoadData(params) {

    try {
        const userData = await fetchUserData();
        populateUserData(userData);
        await loadAndDisplayMedia();
    } catch (error) {
        console.error('Erro na autenticação: ', error.message);
        alert('Sua sessão expirou ou é inválida. Por favor, faça login novamente.');
        window.location.href = 'index.html';
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

        mediaCard.innerHTML = `
            <img src="${media.coverImageUrl || 'assets/images/placeholder.png'}" 
                alt="Capa de ${media.title}" class="media-card-image"> 
            
            <div class="media-card-content">
                <h3 class="media-card-title">${media.title}</h3>
                <p class="media-card-author">${media.author}</p>
                <span class="media-card-status">${formatMediaStatus(media.status)}</span>
                <span class="media-card-rating">Nota: ${media.rating || 'N/A'}</span>

                <div class="media-card-actions">
                    <button type="button" 
                            class="media-card-edit-btn" 
                            data-media-id="${media.id}"
                            data-title="${media.title}"
                            data-status="${media.status}"
                            data-rating="${media.rating || ''}"
                            data-notes="${media.personalNotes || ''}"
                    >
                        Editar
                    </button>
                    <button type="button" 
                            class="media-card-delete-btn" 
                            data-media-id="${media.id}">
                        Excluir
                    </button>
                </div>
            </div>
        `;

        listContainer.appendChild(mediaCard);
    });
}

function openEditModal(editButton) {

    const mediaData = editButton.dataset;

    const editModal = document.getElementById('edit-modal');
    
    document.getElementById('edit-media-id').value = mediaData.mediaId;
    document.getElementById('edit-media-title').textContent = mediaData.title; 
    document.getElementById('edit-media-status').value = mediaData.status;
    document.getElementById('edit-media-rating').value = mediaData.rating;
    document.getElementById('edit-media-notes').value = mediaData.notes;

    editModal.classList.add('is-active');
}


async function handleDeleteMedia(mediaId, buttonElement) {

    const isConfirmed = window.confirm('Tem certeza que deseja excluir esta mídia?');
    if (!isConfirmed) {
        return;
    }
    try {
        await deleteMediaEntry(mediaId);
        const cardToRemove = buttonElement.closest('.media-card');
        if (cardToRemove) {
            cardToRemove.remove(); 
        }
    } catch (error) {
        console.error('Erro ao deletar mídia:', error.message);
        alert('Não foi possível excluir a mídia. Tente novamente.');
    }
}

function formatMediaStatus(statusEnum) {
    switch (statusEnum) {
        case 'WANT_TO_READ':
            return 'Quero Ler';
        case 'READING':
            return 'Lendo';
        case 'COMPLETED':
            return 'Completo';
        case 'DROPPED':
            return 'Dropado';
        case 'ON_PACE':
            return 'Nos Semanais'; 
        case 'HIATUS':
            return 'Em Hiato';
        default:
            return statusEnum; 
    }
}