package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // 파라미터에 타입 + name 모두 적어주기
    // 회원가입, 로그인 모두에서 사용
}