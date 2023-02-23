package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController // Json 형태의 데이터 반환
@RequestMapping("/api") // 클래스의 모든 요청은 우선 해당주소로 받음
public class AllInOneController {
    // 관심상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        // @RequestBody : Client 입력값 -> HTTP BODY에 Json형태로 넘어감 -> 메서드 파라미터에 값을 받아올 객체를 지정

        Product product = new Product(requestDto);
        // requestDto 값 -> 객체 product에 담음 -> DB에 저장

        // DB 직접 연결 -> Connection/DriverManager
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select max(id) as id from product");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // product id 설정 = product 테이블의 마지막 id + 1
            product.setId(rs.getLong("id") + 1);
        } else {
            throw new SQLException("product 테이블의 마지막 id 값을 찾아오지 못했습니다.");
        }
        ps = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice) values(?, ?, ?, ?, ?, ?)");
        ps.setLong(1, product.getId());
        ps.setString(2, product.getTitle());
        ps.setString(3, product.getImage());
        ps.setString(4, product.getLink());
        ps.setInt(5, product.getLprice());
        ps.setInt(6, product.getMyprice());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결해제
        ps.close();
        connection.close();

        // 응답보내기
        return new ProductResponseDto(product);
    }

    // 관심상품 조회하기
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() throws SQLException {
        List<ProductResponseDto> products = new ArrayList<>();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성 및 실행
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from product");

        // DB Query 결과를 상품 객체 리스트로 변환
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLprice(rs.getInt("lprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
            products.add(new ProductResponseDto(product));
        }

        // DB 연결 해제
        rs.close();
        connection.close();

        // 응답 보내기
        return products;
    }

    // 관심상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = new Product();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
        ps.setLong(1, id);

        // DB Query 실행
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLprice(rs.getInt("lprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
        } else {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        // DB Query 작성
        ps = connection.prepareStatement("update product set myprice = ? where id = ?");
        ps.setInt(1, requestDto.getMyprice());
        ps.setLong(2, product.getId());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        rs.close();
        ps.close();
        connection.close();

        // 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }
}
