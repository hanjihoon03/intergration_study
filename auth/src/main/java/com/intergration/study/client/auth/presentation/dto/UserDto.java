package com.intergration.study.client.auth.presentation.dto;

import com.intergration.study.client.auth.model.entity.User;
import com.intergration.study.client.auth.model.entity.UserRoleEnum;
import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@Builder
public record UserDto(Long userId,
                      String username,
                      String email,
                      String password,
                      UserRoleEnum userRoleEnum) {

    public static UserDto of(User user) {
        return UserDto.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .userRoleEnum(user.getUserRoleEnum())
            .build();
    }
}
