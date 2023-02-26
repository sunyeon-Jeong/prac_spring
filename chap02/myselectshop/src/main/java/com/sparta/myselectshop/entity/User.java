package com.sparta.myselectshop.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // class 기본생성자를 자동으로 추가해줌
@Entity(name = "users") // 테이블이름 users (Spring Boot H2에서 User가 예약어로 등록되어 변경 필)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) // 권한, 등급을 나타냄(STRING:Enum이름을 저장함)
    private UserRoleEnum role;

    // 생성자 -> 값 초기화
    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}