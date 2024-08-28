package com.webstrdy00.upgrade_schedule.jwt;

import com.webstrdy00.upgrade_schedule.entity.UserRoleEnum;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/api/*")
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
    public static final Logger logger = LoggerFactory.getLogger("JWT 필터 관련 로그");
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 로그인 및 회원가입 경로 체크
        String path = request.getServletPath();
        logger.info(path);
        if ("/api/users/login".equals(path) || "/api/users/signup".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }


        // Header에서 JWT 토큰 추출
        String token = jwtUtil.substringToken(request.getHeader("Authorization"));

        // 토큰이 없는 경우 400에러 반환
        if (token == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 없습니다.");
            return;
        }

        try {
            // 토큰 유효성 검사
            if (jwtUtil.validateToken(token)){
                String userEmail = jwtUtil.getEmailFromToken(token);
                UserRoleEnum userRole = jwtUtil.getRoleFromToken(token);
                // 인증 정보 설정
                request.setAttribute("userEmail", userEmail);
                request.setAttribute("userRole", userRole);
                logger.info("User authenticated: {}", userEmail);
                logger.info("User authenticated: {}", userRole);
                filterChain.doFilter(request, response);
            }else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다.");
            }
        }catch (ExpiredJwtException e){
            // 토큰 만료 시 401 에러 반환
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었습니다.");
        }catch (Exception e){
            // 기타 토큰 관련 오류 시 401 에러 반환
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }
    }
}
