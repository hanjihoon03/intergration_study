package com.intergration.study.client.auth.presentation.dto;

import com.intergration.study.client.auth.model.entity.UserRoleEnum;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
public record SignUpRequestDto(String username,
                               String password,
                               String email,
                               UserRoleEnum userRoleEnum) {

}
