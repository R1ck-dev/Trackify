package br.com.henrique.trackify.DTOs.Responses;

import lombok.Data;

@Data
public class ResponseToLoginDTO {
    private String token;

    public ResponseToLoginDTO(String token) {
        this.token = token;
    }
    
}
