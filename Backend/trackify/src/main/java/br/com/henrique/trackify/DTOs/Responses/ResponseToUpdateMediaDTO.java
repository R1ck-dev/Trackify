package br.com.henrique.trackify.DTOs.Responses;

import br.com.henrique.trackify.Enums.MediaStatusEnum;
import lombok.Data;

@Data
public class ResponseToUpdateMediaDTO {
    private MediaStatusEnum status;
    private Integer rating;
    private String personalNotes;
}
