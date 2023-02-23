package com.sparta.hanghaeblog.entity;

import com.sparta.hanghaeblog.dto.BlogRequestDto;
import lombok.Builder;
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
    private Long id; // 게시글 번호 PK
    // @Id와 @GeneratedValue 같이 사용함
    // IDENTITY : PK 생성을 DB에 위임 (MySQL = AUTO INCREMENT)
    // id는 Client 쪽(BlogRequestDto)에서 넘어오는 값 없음 -> null을 @GeneratedValue가 값을 지정해줌
    // @Column(nullable = false) -> 이거 넣으면 안됌!!
    private String title; // 제목 PK
    private String username; // 사용자 PK
    private String content; // 작성내용 PK
    private String password; // 비밀번호 PK

    // 빌더패턴 적용 -> 생성자를 통해 값 받기
    @Builder
    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle(); // Blog 필드값(멤버변수) <- requestDto의 값 getter
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }

    // 선택게시글수정 -> Blog 필드값(멤버변수)에 반영하기
    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}