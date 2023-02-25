package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component // 개발자가 생성한 class → Spring Bean 으로 등록
public class ProductService {

    private final ProductRepository productRepository;
    // DB연결

    public ProductService() { // 생성자
        this.productRepository = new ProductRepository();
    }

    // 관심상품 등록하기
    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws SQLException {
        // ProductResponseDto에 반환
        // ProductRequestDto로 Client 입력값 받아옴 (Controller단에서 메서드 실행요청 + 파라미터 보냈음

        Product product = new Product(requestDto);
        // Entity class 객체 생성 -> requestDto값 가져옴

        return  productRepository.createProduct(product);
        // Repository에 해당메서드 실행 후, ResponseDto에 반환
    }

    // 관심상품 조회하기
    public List<ProductResponseDto> getProducts() throws SQLException {
        // ProductResponseDto 리스트 타입으로 반환
        // 조회하기 -> 파라미터로 입력받을 Client 입력값 없음

        return productRepository.getProducts();
        // Repository에 해당메서드 실행 후, ResponseDto에 반환
    }

    // 관심상품 최저가 등록하기
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        // Long 타입의 updateProduct 메서드
        // controller에서 보낸 id, requestDto값 파라미터로 받음

        Product product = productRepository.getProduct(id);
        // Entity class 객체에 <- 앞에서 조회했던 상품의 id값 덮어씌움

        if(product == null) {
            throw new NullPointerException("해당 상품은 존재하지 않습니다.");
        }
        // null 값 예외처리

        return productRepository.updateProduct(product.getId(), requestDto);
        // Repository에 해당메서드 실행 후, 반환 (Entity class 객체의 id값, Client 입력값 파라미터로 보냄)
    }

}
/* 주요 Entity Class의 객체를 만들어
RequestDto 값 가져오고, Repository(DB)에 값 추가/수정/삭제 등 작업.
ResponseDto에 작업한 값 return
 */