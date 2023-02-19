package com.sparta.springjpa.repository;

import com.sparta.springjpa.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
// <해당 entity 객체, 타입>