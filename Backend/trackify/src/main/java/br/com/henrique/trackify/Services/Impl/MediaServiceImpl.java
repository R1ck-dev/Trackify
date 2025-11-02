package br.com.henrique.trackify.Services.Impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.henrique.trackify.DTOs.MediaDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToCreateMediaDTO;
import br.com.henrique.trackify.Models.MediaModel;
import br.com.henrique.trackify.Models.UserMediaEntryModel;
import br.com.henrique.trackify.Models.UserModel;
import br.com.henrique.trackify.Repositories.MediaRepository;
import br.com.henrique.trackify.Repositories.UserMediaEntryRepository;
import br.com.henrique.trackify.Services.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final UserMediaEntryRepository userMediaEntryRepository;

    public MediaServiceImpl(MediaRepository mediaRepository, UserMediaEntryRepository userMediaEntryRepository) {
        this.mediaRepository = mediaRepository;
        this.userMediaEntryRepository = userMediaEntryRepository;
    }

// ---------------------------------------------------------------------------------------------------

    @Override
    public ResponseToCreateMediaDTO createMedia(MediaDTO mediaDTO, Authentication authentication) {
        UserModel currentUser = (UserModel) authentication.getPrincipal();

        // Ou encontra a midia, ou cria.
        MediaModel media = mediaRepository.findByTitleAndAuthor(mediaDTO.getTitle(), mediaDTO.getAuthor())
                .orElseGet(() -> {
                    MediaModel newMedia = new MediaModel();
                    newMedia.setTitle(mediaDTO.getTitle());
                    newMedia.setAuthor(mediaDTO.getAuthor());
                    newMedia.setType(mediaDTO.getType());
                    newMedia.setDescription(mediaDTO.getDescription());
                    newMedia.setCoverImageUrl(mediaDTO.getCoverImageUrl());

                    return mediaRepository.save(newMedia);
                });

        UserMediaEntryModel userMediaEntryModel = new UserMediaEntryModel();
        userMediaEntryModel.setUser(currentUser);
        userMediaEntryModel.setMedia(media);
        userMediaEntryModel.setStatus(mediaDTO.getStatus());
        userMediaEntryModel.setRating(mediaDTO.getRating());
        userMediaEntryModel.setPersonalNotes(mediaDTO.getPersonalNotes());

        userMediaEntryRepository.save(userMediaEntryModel);

        return createMediaToDTO(media);
    }

    public ResponseToCreateMediaDTO createMediaToDTO(MediaModel media) {
        ResponseToCreateMediaDTO response = new ResponseToCreateMediaDTO();

        response.setId(media.getId());
        response.setTitle(media.getTitle());
        response.setAuthor(media.getAuthor());
        response.setType(media.getType());
        response.setDescription(media.getDescription());
        response.setCoverImageUrl(media.getCoverImageUrl());

        return response;
    }
// ---------------------------------------------------------------------------------------------------

}
