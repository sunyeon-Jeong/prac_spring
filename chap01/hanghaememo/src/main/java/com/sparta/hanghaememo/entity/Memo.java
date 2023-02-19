package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*; // persistence : 영속성, Entity를 영구적으로 저장해주는 환경
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped { // 테이블에 대응하는 하나의 클래스
    @Id // 기본키 자동생성
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*public class Timestamped { // Memo 클래스의 조상클래스

        @CreatedDate
        private LocalDateTime createdAt; // 생성된시간

        @LastModifiedDate
        private LocalDateTime modifiedAt; // 변경된시간
    }*/
    private Long id; // 아이디 (PK)

    @Column(nullable = false)
    private String username; // 사용자 (PK)

    @Column(nullable = false)
    private String contents; // 내용 (PK)

    public Memo(MemoRequestDto requestDto) { // Entity Memo 클래스의 생성자
        this.username = requestDto.getUsername();
        // Memo 필드값(멤버변수) <- requestDto의 Username 값 getter(가져와서 변경)
        this.contents = requestDto.getContents();
        // Memo 필드값(멤버변수) <- requestDto의 Contents 값 getter(가져와서 변경)
    }

    // 메모 변경하기
    public void update (MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}