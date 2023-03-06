package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(Long userId);
    // UserId가 동일한 Product를 List형태로 가지고 온다
    Optional<Product> findByIdAndUserId(Long id, Long userId);
    // Product id와 User id가 같으면 일치하는 Product를 List형태로 가지고 온다
}