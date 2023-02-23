package com.sparta.myselectshopbeta.naver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor // 기본생성자 생성해줌
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    // ItemDto의 멤버변수 <- Json 값 getter
    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}