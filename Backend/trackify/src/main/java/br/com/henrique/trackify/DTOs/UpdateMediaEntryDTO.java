package br.com.henrique.trackify.DTOs;

import br.com.henrique.trackify.Enums.MediaStatusEnum;
import lombok.Data;

@Data
public class UpdateMediaEntryDTO {
    private MediaStatusEnum status;
    private Integer rating;
    private String personalNotes;
}
