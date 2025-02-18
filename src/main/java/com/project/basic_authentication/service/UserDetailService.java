package com.project.basic_authentication.service;

import com.project.basic_authentication.entity.UserEntity;
import com.project.basic_authentication.entity.UserPrincipal;
import com.project.basic_authentication.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if(user == null){
            System.out.println("User not found!");
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserPrincipal(user);
    }
}
