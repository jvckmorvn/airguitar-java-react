package com.airguitar.controller;

import com.airguitar.model.dto.LoginRequestDTO;
import com.airguitar.model.dto.TokenResponseDTO;
import com.airguitar.model.dto.UserDTO;
import com.airguitar.service.JWTService;
import com.airguitar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping("/auth/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO request) {
        UserDTO userDTO = userService.authenticate(request.email(), request.password());
        String token = jwtService.generate(userDTO.id());
        return new TokenResponseDTO(token);
    }

}
