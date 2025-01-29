package com.project.basic_authentication.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID userId;
    private String username;
}
