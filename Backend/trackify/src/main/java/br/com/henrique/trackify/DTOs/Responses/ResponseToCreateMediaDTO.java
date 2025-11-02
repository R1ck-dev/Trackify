package br.com.henrique.trackify.DTOs.Responses;

import br.com.henrique.trackify.Enums.MediaTypeEnum;
import lombok.Data;

@Data
public class ResponseToCreateMediaDTO {
    private String id;
    private String title;
    private String author;
    private MediaTypeEnum type;
    private String coverImageUrl;
    private String description;
}
