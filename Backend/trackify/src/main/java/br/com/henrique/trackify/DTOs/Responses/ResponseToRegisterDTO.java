package br.com.henrique.trackify.DTOs.Responses;

import lombok.Data;

@Data
public class ResponseToRegisterDTO {
    private String id;
    private String username;
    private String email;
    
    public ResponseToRegisterDTO() {
    }

}
