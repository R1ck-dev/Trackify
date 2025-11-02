package br.com.henrique.trackify.DTOs.Responses;

import br.com.henrique.trackify.Enums.UserRoleEnum;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private UserRoleEnum role;

    public UserResponseDTO(String id, String username, String email, UserRoleEnum role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

}
