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

    // 메모 생성하기
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index"); // 객체생성, index.html 반환해줌
    }

    @PostMapping("/api/memos")
    public Memo creatMemo(@RequestBody MemoRequestDto requestDto) {
        // POST형식 -> Body가 존재, Body에 데이터 담아올것
        return memoService.createMemo(requestDto);
    }

    // 메모 조회하기
    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    // 메모 변경하기
    @PutMapping("/api/memos/{id}") // put 방식(변경)
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id, requestDto);
    }

    // 메모 삭제하기
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id); // 데이터베이스와 연결
    }
}