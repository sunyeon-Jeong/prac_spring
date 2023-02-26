package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.naver.dto.ItemDto;
import com.sparta.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// @Component // 개발자가 생성한 class → Spring Bean 으로 등록
// @Component 대신, @Service -> 자동 Bean 등록
@Service
@RequiredArgsConstructor // final로 선언된 멤버변수에 자동으로 의존성주입(lombok)
public class ProductService {
    private final ProductRepository productRepository;
    /*
    @RequiredArgsConstructor -> 아래 부분 생략가능
    @Autowired // 스프링에 의해 DI (의존성주입)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        // 멤버변수 <- 객체 생성
    }
*/

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = productRepository.saveAndFlush(new Product(requestDto));
        // saveAndFlush() : 영속성컨텍스트에 보내지 않고, 곧바로 DB에 Insert(save와의 차이점)

        return new ProductResponseDto(product);
    }

    @Transactional (readOnly = true) // 영속성컨텍스트에서 read 기능만 함 (rollback 안함)
    public List<ProductResponseDto> getProducts() {

        List<ProductResponseDto> list = new ArrayList<>();

        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            list.add(new ProductResponseDto(product));
        }

        return list;
    }

    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
        );

        product.update(requestDto);

        return product.getId();
    }

    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
        );
        product.updateByItemDto(itemDto);
    }

}