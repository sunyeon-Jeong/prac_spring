package com.sparta.hanghaememo.repository;


import com.sparta.hanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc(); // DB에서 메모조회할 것 가져오기
    // 수정된 시간이 가장 최근인 순서대로 가져옴 -> 메모가 가장위에 올라옴 (내림차순)
} // 데이터베이스 연결
// Thymeleaf 가 알아서 나머지 구현