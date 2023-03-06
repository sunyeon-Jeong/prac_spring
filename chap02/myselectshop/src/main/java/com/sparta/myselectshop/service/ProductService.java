package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.entity.UserRoleEnum;
import com.sparta.myselectshop.jwt.JwtUtil;
import com.sparta.myselectshop.naver.dto.ItemDto;
import com.sparta.myselectshop.repository.ProductRepository;
import com.sparta.myselectshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // user 검증을 위함

    // 관심상품 등록하기
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = productRepository.saveAndFlush(new Product(requestDto));
        // saveAndFlush() : 영속성컨텍스트에 보내지 않고, 곧바로 DB에 Insert(save와의 차이점)

        return new ProductResponseDto(product);
    }

    // 관심상품 조회하기
//    @Transactional(readOnly = true)
//    public List<ProductResponseDto> getProducts() {
//        List<ProductResponseDto> list = new ArrayList<>();
//        List<Product> productList = productRepository.findAll(); // 모든 상품을 조회함
//        for (Product product : productList) {
//            list.add(new ProductResponseDto(product));
//        }
//        return list;
//    }
    @Transactional (readOnly = true) // 영속성컨텍스트에서 read 기능만 함 (rollback 안함)
    public List<ProductResponseDto> getProducts(HttpServletRequest request) {
        // Request에서 Token 가져옴(Client가 요청시 token을 같이 넘겨 세션 유지)
        String token = jwtUtil.resolveToken(request); // request Header에서 토큰 가져옴
        Claims claims; // JWT 안에있는 정보를 담을 수 있는 객체

        if (token != null) { // 1. 토큰값 있을 경우 -> 관심상품 조회 가능
            // 2. 토큰 검증단계
            if (jwtUtil.validateToken(token)) { // validateToken 메서드를 통해 토큰 검증
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 3. 토큰에서 가져온 사용자 정보 claims -> DB조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            // 4. 사용자 권한 -> ADMIN이면 전제조회, USER이면 본인이 추가한 부분만 조회
            UserRoleEnum userRoleEnum = user.getRole();
            System.out.println("role = " + userRoleEnum);

            List<ProductResponseDto> list = new ArrayList<>();
            List<Product> productList;

            if (userRoleEnum == UserRoleEnum.USER) {
                // 사용자 권한 USER
                productList = productRepository.findAllByUserId(user.getId());
            } else {
                productList = productRepository.findAll();
            }
            for (Product product : productList) {
                list.add(new ProductResponseDto(product));
            }
            return list;
        } else {
            return null;
        }
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