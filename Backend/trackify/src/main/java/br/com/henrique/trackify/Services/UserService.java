package br.com.henrique.trackify.Services;

import br.com.henrique.trackify.DTOs.UserDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToRegisterDTO;
import br.com.henrique.trackify.DTOs.Responses.UserResponseDTO;
import br.com.henrique.trackify.Models.UserModel;

public interface UserService {
    ResponseToRegisterDTO registerUser(UserDTO userDTO);
    UserResponseDTO userResponse(UserModel currentUser);
}
