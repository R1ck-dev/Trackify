package br.com.henrique.trackify.Services;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.henrique.trackify.DTOs.MediaDTO;
import br.com.henrique.trackify.DTOs.UpdateMediaEntryDTO;
import br.com.henrique.trackify.DTOs.Responses.GetMediasFromUserDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToCreateMediaDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToUpdateMediaDTO;
public interface MediaService {
    ResponseToCreateMediaDTO createMedia(MediaDTO mediaDTO, Authentication authentication);
    List<GetMediasFromUserDTO> getMyMedia(Authentication authentication);
    void deleteMediaEntry(String mediaId, Authentication authentication);
    ResponseToUpdateMediaDTO updateMedia(String mediaId, UpdateMediaEntryDTO updateData, Authentication authentication);
}
