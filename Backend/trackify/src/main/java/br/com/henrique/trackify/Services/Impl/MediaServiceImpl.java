package br.com.henrique.trackify.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.henrique.trackify.DTOs.MediaDTO;
import br.com.henrique.trackify.DTOs.Responses.GetMediasFromUserDTO;
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

    @Override
    public List<GetMediasFromUserDTO> getMyMedia(Authentication authentication) {
        UserModel currentUser = (UserModel) authentication.getPrincipal();

        List<UserMediaEntryModel> mediaList =  userMediaEntryRepository.findByUser(currentUser);

        return transformMediaToDTO(mediaList);

    }

    public List<GetMediasFromUserDTO> transformMediaToDTO(List<UserMediaEntryModel> mediaList) {
        
        List<GetMediasFromUserDTO> responseDTOList = mediaList.stream()
                .map(entry -> {
                    GetMediasFromUserDTO dto = new GetMediasFromUserDTO();

                    dto.setId(entry.getId());
                    dto.setStatus(entry.getStatus());
                    dto.setRating(entry.getRating());

                    dto.setTitle(entry.getMedia().getTitle());
                    dto.setAuthor(entry.getMedia().getAuthor());
                    dto.setCoverImageUrl(entry.getMedia().getCoverImageUrl());

                    return dto;
                })
                .collect(Collectors.toList());
            
            return responseDTOList;
    }
// ---------------------------------------------------------------------------------------------------

}
