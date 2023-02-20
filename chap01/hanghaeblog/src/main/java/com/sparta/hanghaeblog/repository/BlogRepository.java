package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// DB 연결과정
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByModifiedAtDesc(); // DB에서 게시글 조회할 것 가져옴
    // 수정된 시간이 가장 최근인 순서대로 가져옴 -> 옛날 게시글 가장위에 올라옴 (내림차순)
}
// Thymeleaf가 알아서 나머지 구현함