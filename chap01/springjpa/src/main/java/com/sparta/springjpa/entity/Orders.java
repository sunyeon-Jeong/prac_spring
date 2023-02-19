package com.sparta.springjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 값을 가져올 때 사용
@Entity
@NoArgsConstructor // 기본생성자
public class Orders {
    @Id // PK 변수 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 DB에 위임
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Orders(Food food, Member member) { // 생성자
        this.food = food;
        this.member = member;
    }
}