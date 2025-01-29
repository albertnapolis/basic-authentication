package com.project.basic_authentication.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestDTO {
    private UUID userId;
    private String username;
    private String password;
}
