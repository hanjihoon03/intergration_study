package com.intergration.study.client.auth.common.security.jwt;

import com.intergration.study.client.auth.common.security.UserDetailsImpl;
import com.intergration.study.client.auth.common.security.UserDetailsServiceImpl;
import com.intergration.study.client.auth.presentation.dto.UserResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain chain) throws ServletException, IOException {

        String token = tokenProvider.resolveToken(request);

        if (token != null && tokenProvider.validateToken(token)) {
            // Redis에서 블랙리스트 확인
            if (redisTemplate.hasKey("blacklist:" + token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is blacklisted");
            }

            Claims userInfoFromToken = tokenProvider.getUserInfoFromToken(token);

            try {
                setAuthentication(userInfoFromToken.getSubject());
            } catch (Exception e) {
                log.error(e.getMessage());

            }

        }
        chain.doFilter(request, response);

    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);


        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }
}
