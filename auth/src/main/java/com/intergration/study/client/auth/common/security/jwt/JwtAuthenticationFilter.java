package com.intergration.study.client.auth.common.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intergration.study.client.auth.common.security.UserDetailsImpl;
import com.intergration.study.client.auth.model.entity.User;
import com.intergration.study.client.auth.model.entity.UserRoleEnum;
import com.intergration.study.client.auth.presentation.dto.LoginRequestDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;


    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, RedisTemplate<String, String> redisTemplate) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
                LoginRequestDto.class);
            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    requestDto.username(),
                    requestDto.password(),
                    null
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(filterProcessesUrl, "POST"));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getRole();




        String token = cacheRedis(username, role);

        response.addHeader(JwtTokenProvider.AUTHORIZATION_HEADER, token);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
    private String cacheRedis(String username, UserRoleEnum role) {
        String token = jwtTokenProvider.createToken(username, role);

        log.info("token: {}", token);
        redisTemplate.opsForValue().set("token:" + token, role.getAuthority() , Duration.ofHours(3));
        return token;
    }
}
