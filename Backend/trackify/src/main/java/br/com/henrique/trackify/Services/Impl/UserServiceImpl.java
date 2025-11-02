package br.com.henrique.trackify.Services.Impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.henrique.trackify.DTOs.UserDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToRegisterDTO;
import br.com.henrique.trackify.DTOs.Responses.UserResponseDTO;
import br.com.henrique.trackify.Enums.UserRoleEnum;
import br.com.henrique.trackify.Models.UserModel;
import br.com.henrique.trackify.Repositories.UserRepository;
import br.com.henrique.trackify.Services.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

// -------------------------------------------------------------------------------------------------

    @Override
    public ResponseToRegisterDTO registerUser(UserDTO userDTO) {
        UserModel userModel = new UserModel();

        userModel.setUsername(userDTO.getUsername());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userModel.setRole(UserRoleEnum.ROLE_ADMIN); // Temporariamente

        userRepository.save(userModel);

        return registerToDTO(userDTO, userModel.getId());
    }

    public ResponseToRegisterDTO registerToDTO(UserDTO userDTO, String id) {
        ResponseToRegisterDTO dto = new ResponseToRegisterDTO();

        dto.setId(id);
        dto.setUsername(userDTO.getUsername());
        dto.setEmail(userDTO.getEmail());

        return dto;
    }
// -------------------------------------------------------------------------------------------------
    @Override
    public UserResponseDTO userResponse(UserModel currentUser) {
        UserResponseDTO responseDTO = new UserResponseDTO(
            currentUser.getId(),
            currentUser.getDisplayName(),
            currentUser.getEmail(),
            currentUser.getRole()
        );

        return responseDTO;
    }

}
