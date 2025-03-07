package com.intergration.study.client.auth.presentation.dto;

import com.intergration.study.client.auth.model.entity.User;
import com.intergration.study.client.auth.model.entity.UserRoleEnum;
import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@Builder
public record UserResponseDto(Long userId,
                              String username,
                              String email,
                              UserRoleEnum userRoleEnum,
                              String accessToken) {

    public static UserResponseDto from(User user, String accessToken) {
        return UserResponseDto.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .userRoleEnum(user.getUserRoleEnum())
            .accessToken(accessToken)
            .build();
    }
}
