package com.sparta.myselectshop.entity;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.naver.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter // private 필드값을 가져옴
@Setter // getter로 가져 온 private 필드값 -> 수정함
@Entity // DB 테이블 역할
@NoArgsConstructor // 파라미터가 없는 기본생성자 생성
public class Product extends Timestamped { // Entity class Product

    @Id // 기본키(PK) 자동생성 -> 영속성 entity 필수조건
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가!
    private Long id;
    // @GeneratedValue : null 값을 먼저 받은 후 -> 값을 지정해줌 -> nullable = false 절대 안됌!!!

    @Column(nullable = false)
    private String title; // 관심상품명

    @Column(nullable = false)
    private String image; // 관심상품 썸네일이미지 url

    @Column(nullable = false)
    private String link; // 관심상품 구매링크 url

    @Column(nullable = false)
    private int lprice; // 관심상품 최저가

    @Column(nullable = false)
    private int myprice; // 내가 지정한 금액

    @Column(nullable = false)
    private Long userId; // user에 id를 넣어줌

    // 생성자
    public Product(ProductRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle(); // Client가 request하는 값 -> getter -> entity class 필드값 덮어씌우기
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0; // 생성자 이용 -> 내가 지정한 금액 0으로 셋팅
        this.userId = userId;
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myprice = requestDto.getMyprice();
    }

    // 상품최저가 스케줄러
    public void updateByItemDto(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }
}