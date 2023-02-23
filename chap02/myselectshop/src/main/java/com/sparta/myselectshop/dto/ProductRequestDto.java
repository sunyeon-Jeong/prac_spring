package com.sparta.myselectshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 파라미터가 없는 기본생성자 생성
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자 만듦
public class ProductRequestDto { // Client로부터 요청받은 값
    // 관심상품명
    private String title;

    // 관심상품 썸네일 image URL
    private String image;

    // 관심상품 구매링크 URL
    private String link;

    // 관심상품의 최저가
    private int lprice;
}