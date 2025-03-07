package com.intergration.study.client.auth.presentation.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */

public record LoginRequestDto(String username,
                              String password) {


}
