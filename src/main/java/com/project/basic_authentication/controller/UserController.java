package com.project.basic_authentication.controller;

import com.project.basic_authentication.dto.LoginRequestDTO;
import com.project.basic_authentication.dto.UserRequestDTO;
import com.project.basic_authentication.dto.UserResponseDTO;
import com.project.basic_authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return "Success";
    }
}
