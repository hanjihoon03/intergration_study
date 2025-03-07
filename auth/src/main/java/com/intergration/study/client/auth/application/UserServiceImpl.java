package com.intergration.study.client.auth.application;

import com.intergration.study.client.auth.model.entity.User;
import com.intergration.study.client.auth.model.repository.UserRepository;
import com.intergration.study.client.auth.presentation.dto.LoginRequestDto;
import com.intergration.study.client.auth.presentation.dto.SignUpRequestDto;
import com.intergration.study.client.auth.presentation.dto.UserDto;
import com.intergration.study.client.auth.presentation.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.of(signUpRequestDto);
        user.encodeUserPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserDto.of(user);
    }

}
