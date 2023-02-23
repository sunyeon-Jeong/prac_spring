package com.sparta.myselectshopbeta.naver.controller;

import com.sparta.myselectshopbeta.naver.dto.ItemDto;
import com.sparta.myselectshopbeta.naver.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Json 형태의 데이터 반환
@RequestMapping("/api") // 클래스의 모든 요청은 우선 해당주소로 받음
@RequiredArgsConstructor // final, @Notnull -> 필드의 생성자 자동생성
public class NaverApiController {
    // 서비스단 연결
    private final NaverApiService naverApiService;

    // 상품검색
    @GetMapping("/search") // 검색 -> GET방식
    public List<ItemDto> searchItems(@RequestParam String query)  {
        // @RequestParam : URL에 전달되는 파라미터 -> 메서드 인자에 받아 처리
        // Get방식으로 요청 -> URL에 값 표출(POST와 반대)
        return naverApiService.searchItems(query);
        // 서비스단에 searchItems 메서드 실행 (파라미터로 query 값 넘겨받음)
        // -> Dto에 리스트 타입으로 반환
    }
}