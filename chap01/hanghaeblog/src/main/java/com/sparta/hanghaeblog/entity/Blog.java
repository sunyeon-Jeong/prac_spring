package com.sparta.hanghaeblog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity; // persistence : 영속성, Entity를 영구적으로 저장
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 모든 필드(변수)의 Getter method 생성
@Entity // 엔티티클래스임을 선언
@NoArgsConstructor // 클래스 기본생성자 자동추가
public class Blog extends Timestamped {
    @Id // 기본키(PK) 자동생성 -> 영속성 entity 필수조건
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Id와 @GeneratedValue 같이 사용함
    // IDENTITY : PK 생성을 DB에 위임 (MySQL = AUTO INCREMENT)
    private Long title; // 제목 PK
    private String user; // 사용자 PK
    private String content; // 작성내용 PK
    private Long id; // 게시글 번호 PK
    private String password; // 비밀번호 PK
}