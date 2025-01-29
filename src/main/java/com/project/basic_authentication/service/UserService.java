package com.project.basic_authentication.service;

import com.project.basic_authentication.dto.UserRequestDTO;
import com.project.basic_authentication.dto.UserResponseDTO;
import com.project.basic_authentication.entity.UserEntity;
import com.project.basic_authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        UserEntity saveUser = userRepository.save(userEntity);
        return convertToResponseDTO(saveUser);
    }

    public static UserResponseDTO convertToResponseDTO(UserEntity userEntity) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(userEntity.getUserId());
        userResponseDTO.setUsername(userEntity.getUsername());
        return userResponseDTO;
    }
}
