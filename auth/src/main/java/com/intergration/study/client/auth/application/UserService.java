package com.intergration.study.client.auth.application;

import com.intergration.study.client.auth.presentation.dto.LoginRequestDto;
import com.intergration.study.client.auth.presentation.dto.SignUpRequestDto;
import com.intergration.study.client.auth.presentation.dto.UserDto;
import com.intergration.study.client.auth.presentation.dto.UserResponseDto;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
public interface UserService {

    UserDto signUp(SignUpRequestDto signUpRequestDto);
}
