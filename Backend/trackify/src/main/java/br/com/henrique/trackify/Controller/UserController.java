package br.com.henrique.trackify.Controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.trackify.DTOs.Responses.UserResponseDTO;
import br.com.henrique.trackify.Models.UserModel;
import br.com.henrique.trackify.Services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(Authentication authentication) {

        UserModel currentUser = (UserModel) authentication.getPrincipal();

        UserResponseDTO responseDTO = userService.userResponse(currentUser);

        return ResponseEntity.ok(responseDTO);
    }

}
