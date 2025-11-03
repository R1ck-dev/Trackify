package br.com.henrique.trackify.DTOs.Responses;

import br.com.henrique.trackify.Enums.MediaStatusEnum;
import lombok.Data;

@Data
public class GetMediasFromUserDTO {
    private String id;
    private String title;
    private String author;
    private MediaStatusEnum status;
    private Integer rating;
    private String coverImageUrl;
}
