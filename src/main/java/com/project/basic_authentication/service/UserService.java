package com.project.basic_authentication.service;

import com.project.basic_authentication.dto.LoginRequestDTO;
import com.project.basic_authentication.dto.UserRequestDTO;
import com.project.basic_authentication.dto.UserResponseDTO;
import com.project.basic_authentication.entity.UserEntity;
import com.project.basic_authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    public String verify(LoginRequestDTO loginRequestDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(loginRequestDTO.getUsername());
        user.setPassword(loginRequestDTO.getPassword());

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}
