package com.sparta.hanghaeblog.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter // 모든 필드(변수)의 Getter method 생성
@MappedSuperclass // 부모클래스 -> 테이블 Mapping X, 자식클래스 -> Mapping 정보만 제공
// 단순히 Mapping 정보만 상속
@EntityListeners(AuditingEntityListener.class) // 해당 Entity의 변화 감지 -> 테이블의 데이터 조작
public class Timestamped {
    @CreatedDate
    private LocalDateTime createdAt; // 생성된 시간

    @LastModifiedDate
    private LocalDateTime modifiedAt; // 변경된 시간
}