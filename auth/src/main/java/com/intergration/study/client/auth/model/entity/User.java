package com.intergration.study.client.auth.model.entity;

import com.intergration.study.client.auth.presentation.dto.SignUpRequestDto;
import com.intergration.study.client.auth.presentation.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public static User of(SignUpRequestDto signUpRequestDto) {
        return User.builder()
            .username(signUpRequestDto.username())
            .email(signUpRequestDto.email())
            .password(signUpRequestDto.password())
            .userRoleEnum(signUpRequestDto.userRoleEnum())
            .build();
    }
    public void encodeUserPassword(String password) {
        this.password = password;
    }

}
