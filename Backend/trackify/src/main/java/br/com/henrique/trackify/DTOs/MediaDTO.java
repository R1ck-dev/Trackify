package br.com.henrique.trackify.DTOs;

import br.com.henrique.trackify.Enums.MediaStatusEnum;
import br.com.henrique.trackify.Enums.MediaTypeEnum;
import lombok.Data;

@Data
public class MediaDTO {
    // private String id;
    private String title;
    private String author;
    private MediaTypeEnum type;
    private MediaStatusEnum status;
    private String coverImageUrl;
    private String description;
    private Integer rating;
    private String personalNotes;
}
