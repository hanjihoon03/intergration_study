package com.intergration.study.client.auth.presentation;

import com.intergration.study.client.auth.application.UserService;
import com.intergration.study.client.auth.application.UserServiceImpl;
import com.intergration.study.client.auth.presentation.dto.LoginRequestDto;
import com.intergration.study.client.auth.presentation.dto.SignUpRequestDto;
import com.intergration.study.client.auth.presentation.dto.UserDto;
import com.intergration.study.client.auth.presentation.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(userService.signUp(signUpRequestDto));
    }

}
