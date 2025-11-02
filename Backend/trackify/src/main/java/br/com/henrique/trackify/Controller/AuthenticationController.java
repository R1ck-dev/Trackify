package br.com.henrique.trackify.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.trackify.DTOs.LoginDTO;
import br.com.henrique.trackify.DTOs.UserDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToLoginDTO;
import br.com.henrique.trackify.DTOs.Responses.ResponseToRegisterDTO;
import br.com.henrique.trackify.Models.UserModel;
import br.com.henrique.trackify.Security.TokenService;
import br.com.henrique.trackify.Services.UserService;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<ResponseToRegisterDTO> registerUser(@RequestBody UserDTO userDTO) {
        ResponseToRegisterDTO newUser = userService.registerUser(userDTO);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        
        return ResponseEntity.created(location).body(newUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseToLoginDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        
        return ResponseEntity.ok(new ResponseToLoginDTO(token));
    }
    
}
