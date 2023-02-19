package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 클라이언트에게 메인페이지를 보여주는 요청을 받기위한 컨트롤러

@RestController // Json형태의 데이터 반환
@RequiredArgsConstructor // final, @Notnull -> 필드의 생성자를 자동으로 만들어줌
public class MemoController {
    private final MemoService memoService; // 서비스부분 불러옴

    // 메인페이지
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index"); // 객체생성, index.html 반환해줌
    }

    // 메모 생성하기
    @PostMapping("/api/memos") // 생성 -> POST 형식
    public Memo creatMemo(@RequestBody MemoRequestDto requestDto) {
        // POST형식 -> Body가 존재, Body에 데이터 담아올것
        // Entity Memo클래스의 메서드
        // requestDto 데이터를(Json형태) 받아 -> @Service에 createMemo 메서드에 넣은 것 반환
        return memoService.createMemo(requestDto);
    }

    // 메모 조회하기
    @GetMapping("/api/memos") // 조회 -> GET 방식
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    // 메모 변경하기
    @PutMapping("/api/memos/{id}") // put 방식(변경)
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        // @PathVariable : URL을 통해 전달된 값(id) -> 파라미터로 받아옴
        return memoService.update(id, requestDto);
        // @Service에 updateMemo 전달 (URL로 받은 id, DTO에 있던 사용자 + 내용)
    }

    // 메모 삭제하기
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id); // 데이터베이스와 연결, 생성자로 id 값 가져감
    }
}