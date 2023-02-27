package com.sparta.myselectshop.jwt;


import com.sparta.myselectshop.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component // Spring Bean 등록
@RequiredArgsConstructor // final, @Notnull 필드 -> 의존성 주입
public class JwtUtil {
    // 토큰 생성에 필요한 값
    // Response Header에 전달된 JWT 형태 -> Authorization: BEARER <JWT>
    public static final String AUTHORIZATION_HEADER = "Authorization"; // Header Key 값
    public static final String AUTHORIZATION_KEY = "auth"; // 사용자 권한값 Key
    private static final String BEARER_PREFIX = "Bearer "; // Token 식별자
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // Token 만료시간 (ms기준 -> 1시간설정)

    @Value("${jwt.secret.key}") // application.properties에 Token 값 가져옴
    private String secretKey; // 서버에서 Client가 전달한 JWT 위조여부 검증함
    private Key key; // key 객체 : Token 생성 시, 넣어줄 key값
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct // 객체 생성 시, 초기화하는 함수
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey); // secretKey 디코딩 -> bytes 변수에 담기
        key = Keys.hmacShaKeyFor(bytes); // 메서드 사용해서 key에 담아주기
    }

    // header 토큰을 가져오기
    public String resolveToken(HttpServletRequest request) {
        // HttpServletRequest의 request 객체에 값 담아옴
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); // 파라미터로 key값 지정 -> 값 가져옴
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            // Header가 bearerToken이 있는지 + Bearer_Pprefix로 시작하는지 확인
            return bearerToken.substring(7); // 앞의 7글자 지우고 가져옴(Token과 관련없는 Bearer)
        }
        return null;
    }

    // JWT 토큰 생성
    public String createToken(String username, UserRoleEnum role) { // 사용자이름, 회원등급 파라미터로 받음
        Date date = new Date(); // 날짜 객체 생성

        return BEARER_PREFIX + // Bearer 앞에 붙임(식별자)
                Jwts.builder() // Jwts 빌드
                        .setSubject(username) // Subject라는 공간 <- username
                        .claim(AUTHORIZATION_KEY, role) // Auth키를 사용해서 claim <- A 사용자 권한
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 토큰 유효기간 <- date객체.getTime 현재시간 + 위에서 지정해준 만료시간
                        .setIssuedAt(date) // 토큰 생성된 시간
                        .signWith(key, signatureAlgorithm) // Secret Key를 통해 만든 key값과, key객체를 암호화 할 알고리즘
                        .compact(); // String 형태의 JWT 토큰 반환
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        // getBody()로 안에 있는 정보 가져옴(검증)
    }

}