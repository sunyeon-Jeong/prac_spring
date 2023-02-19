package com.sparta.springjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter // 값을 가져올 때 사용
@Entity
@NoArgsConstructor // 기본생성자
public class Member {
    @Id // PK 변수 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 DB에 위임
    private Long id; // Column값
    @Column(nullable = false) // null 허용 X, 객체필드와 DB컬럼 mapping
    private String memberName;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // JPA연관관계 : 1대N
    private List<Orders> orders = new ArrayList<>();

    public Member(String memberName) { // 생성자 : 초기값 셋팅해줌
        this.memberName = memberName;
    }
}