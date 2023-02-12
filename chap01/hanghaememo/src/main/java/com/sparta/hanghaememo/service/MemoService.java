package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository; // DB연결하는 MemoRepository에 연결

    @Transactional // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo); // save메서드 안에 memo 인스턴스 -> 쿼리생성+DB연결
        return memo;
    }

    // 메모 조회하기
    @Transactional(readOnly = true) // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public List<Memo> getMemos() {
        /*return memoRepository.findAll(); // findAll -> DB에서 저장된 데이터 모두 가져옴*/
        return memoRepository.findAllByOrderByModifiedAtDesc(); // 내림차순으로 데이터 가져옴
    }

    // 메모 변경하기
    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                // 실제로 데이터베이스에 수정해야할 메모가 있는지 없는지 확인함
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                // 예외처리 -> 예외발생 시 반환 메시지
        );
        memo.update(requestDto); // 메모 값 변경, requestDto 값으로 변경해줌
        return memo.getId();
    }

    // 메모 삭제하기
    @Transactional
    public Long deleteMemo(Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}