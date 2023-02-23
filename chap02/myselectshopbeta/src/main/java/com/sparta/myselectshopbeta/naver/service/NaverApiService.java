package com.sparta.myselectshopbeta.naver.service;

import com.sparta.myselectshopbeta.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j // 로그를 남김
@Service
public class NaverApiService {
    // 상품검색
    public List<ItemDto> searchItems(String query) {
        // 컨트롤러에서 넘어온 Param query 값
        // ItemDto에 리스트 타입으로 반환함

        RestTemplate rest = new RestTemplate(); // RestTemplate 객체 rest 생성
        HttpHeaders headers = new HttpHeaders(); // HttpHeaders의 객체 headers 생성
        headers.add("X-Naver-Client-Id", "lZyVXSfqNdg2R4hI0kWa");
        // 네이버 API Client id
        headers.add("X-Naver-Client-Secret", "6cD7UDBhlo");
        // 네이버 API Client token
        String body = ""; // Body 공백

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query , HttpMethod.GET, requestEntity, String.class);
        // display=15 -> 검색결과를 총 15개 보여준다는 뜻
        // HttpEntity 클래스 : Http 요청, 응답에 해당하는 HttpHeader와 HttpBody를 가짐
        // HttpEntity 클래스를 상속받아 구현한 클래스 -> requestEntity, responseEntity

        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code : " + status);

        String response = responseEntity.getBody();
        // responseEntity의 바디에 있는 값을 -> String 타입 response에 담아옴

        return fromJSONtoItems(response); // 메서드에 값을 담아 반환
    }

    public List<ItemDto> fromJSONtoItems(String response) {
    // 반환타입이 Dto 리스트형태, 해당메서드는 responseEntity 바디값(Naver 검색결과값)을 인자로 받음

        // String(검색결과) -> DTO로 바꾸기 위해 build.gradle > dependencies에 import
        JSONObject rjson = new JSONObject(response); // 문자열 정보 -> JSONObject로 바꿈
        JSONArray items  = rjson.getJSONArray("items"); // JSONObject -> items 배열 꺼냄

        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        } // JSONArray -> for문 돌면서 item(상품) 하나씩 -> ItemDto에 변환

        return itemDtoList;
    }
}