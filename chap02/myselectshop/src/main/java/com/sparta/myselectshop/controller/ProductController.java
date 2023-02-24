package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController // Json 형태의 데이터 반환
@RequestMapping("/api") // 클래스의 모든 요청은 우선 해당주소로 받음
public class ProductController {

    // 관심상품 등록하기
    @PostMapping("/products") // 등록 -> POST
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        // @RequestBody : Client 입력값 -> HTTP BODY에 Json형태로 넘어감 -> 메서드 파라미터에 값을 받아올 객체를 지정
        // 예외 : SQLException으로 던짐
        ProductService productService = new ProductService();
        // Service 객체 만들어서 연결
        return productService.createProduct(requestDto);
        // Client 입력값을 파라미터로 받아 -> 서비스단의 createProduct 메서드 실행 후, return
    }

    // 관심상품 조회하기
    @GetMapping("/products") // 조회 -> GET
    public List<ProductResponseDto> getProducts() throws SQLException {
        // ResponseDto List 형태로 반환
        // 예외 : SQLException으로 던짐
        ProductService productService = new ProductService();
        // Service 객체 만들어서 연결
        return productService.getProducts();
        // 서비스단의 getProducts 메서드 실행 후, return
    }

    // 관심상품 최저가 등록하기
    @PutMapping("/products/{id}") // 등록 -> POST
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        // Long타입 updateProduct메서드 실행
        // @PathVariable : url을 통해 전달된 값 -> 파라미터로 받아옴
        // @RequestBody : Client 입력값 → HTTP Body에 Json형태로 넘어감 → 파라미터에 @RequestBody + 값을 받아올 객체 지정해줌
        // Client가 입력한 관심상품최저가() -> 파라미터로 받아옴
        ProductService productService = new ProductService();
        // Service 객체 만들어서 연결
        return productService.updateProduct(id, requestDto);
        // (업데이트된 상품 id, 클라이언트 입력 상품최저가) -> Service단의 updateProduct 메서드 실행
    }
}

/*
Controller -> Client request 받아서 Service단으로 보내주는 역할만 수행!!!
 */