package com.webstrdy00.upgrade_schedule.jwt;

import com.webstrdy00.upgrade_schedule.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String AUTHORIZATION_KEY = "role";

    @Value("${jwt.secret.key}")
    private String secret;
    private Key key;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @PostConstruct
    public void init() {
            byte[] bytes = Base64.getDecoder().decode(secret);
            key = Keys.hmacShaKeyFor(bytes);
    }

    // jwt 토큰 생성
    public String createToken(String email, UserRoleEnum role){
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                .setSubject(email)
                .claim(AUTHORIZATION_KEY, role.name())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expiration))
                .signWith(signatureAlgorithm, key)
                .compact();
    }

    // jwt 토큰에서 사용자명 추출
    public String substringToken(String token){
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)){
            return token.substring(7);
        }
        logger.error("토큰을 찾을 수 없습니다.");
        throw new NullPointerException("토큰을 찾을 수 없습니다.");
    }

    // jwt 토큰 유효성 검사
    public boolean validateToken(String token){
        try {
            logger.info("토큰 검증 :" + token);
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);

            // 1. JWT 파셔 빌드
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
            logger.info("JWT parser 성공");

            // 2. 토큰 파싱
            Jws<Claims> jwt = jwtParser.parseClaimsJws(token);
            logger.info("Token parser 성공");

            // 3. 클레임 추출
            Claims claims = jwt.getBody();
            logger.info("Claims extracted : ", claims);

            // 클레임 세부 정보 로깅
            logger.info("Subject: {}", claims.getSubject());
            logger.info("Issued At: {}", claims.getIssuedAt());
            logger.info("Expiration: {}", claims.getExpiration());

            logger.info("토큰 검증 성공");
            return true;
        }catch (SecurityException | MalformedJwtException | SignatureException e) {
            logger.error("Invalid JWT signature", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty", e.getMessage());
        }
        return false;
    }


    // 토큰에서 사용자 정보 가져오기
    private Claims getUserInfoFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    // 토큰에서 이메일 추출
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰에서 권한 정보 추출
    public UserRoleEnum getRoleFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String role = claims.get(AUTHORIZATION_KEY, String.class);
        logger.info(role);
        return UserRoleEnum.valueOf(role);
    }
}
