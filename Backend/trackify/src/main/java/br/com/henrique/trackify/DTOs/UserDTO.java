package br.com.henrique.trackify.DTOs;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
}
