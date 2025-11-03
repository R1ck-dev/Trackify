package br.com.henrique.trackify.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.trackify.DTOs.MediaDTO;
import br.com.henrique.trackify.DTOs.Responses.GetMediasFromUserDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToCreateMediaDTO;
import br.com.henrique.trackify.Services.MediaService;

import org.springframework.security.core.Authentication;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<ResponseToCreateMediaDTO> createMedia(@RequestBody MediaDTO mediaDTO,
            Authentication authentication) {
        ResponseToCreateMediaDTO response = mediaService.createMedia(mediaDTO, authentication);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GetMediasFromUserDTO>> getMyMedias(Authentication authentication) {
        
        List<GetMediasFromUserDTO> response = mediaService.getMyMedia(authentication);

        return ResponseEntity.ok(response);
    }
    
}
