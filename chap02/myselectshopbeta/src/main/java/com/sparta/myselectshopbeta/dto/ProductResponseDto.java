package com.sparta.myselectshopbeta.dto;

import com.sparta.myselectshopbeta.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String link;
    private String image;
    private int lprice;
    private int myprice;

    public ProductResponseDto(Product product) { // Entity class의 객체
        this.id = product.getId(); // Entity 객체로 부터 값 가져와서 덮어씌움 -> 반환
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();
    }
}