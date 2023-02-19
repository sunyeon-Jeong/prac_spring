package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service // Service 어노테이션
@RequiredArgsConstructor // final, @Notnull 붙은 것 -> 생성자 자동지정
public class MemoService {
    private final MemoRepository memoRepository; // DB연결하는 MemoRepository에 연결

    @Transactional // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public Memo createMemo(MemoRequestDto requestDto) { // Dto에 담아온 사용자, 내용 값 파라미터로 받음
        // Memo -> 반환타입(Memo 클래스의 객체를 반환)
        Memo memo = new Memo(requestDto); // Memo 클래스의 memo객체 생성
        memoRepository.save(memo); // save메서드 안에 memo 인스턴스 -> 쿼리생성+DB연결
        // DB에 값 저장
        return memo; // 사용자, 내용 return
    }

    // 메모 조회하기
    @Transactional(readOnly = true) // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public List<Memo> getMemos() {
        // List<Memo> -> 반환타입(Memo 클래스를 리스트형태로 반환)
        /*return memoRepository.findAll(); // findAll -> DB에서 저장된 데이터 모두 가져옴*/
        return memoRepository.findAllByOrderByModifiedAtDesc();
        // 연결된 DB에서 내림차순으로 데이터 가져옴
    }

    // 메모 변경하기
    @Transactional // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                // 실제로 데이터베이스에 수정해야할 메모가 있는지 없는지 확인함
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                // RuntimeException -> 적합, 적절하지 못한 인자를 메서드에 넘겨주었을 때
                // rollback 함
                // 예외처리 -> 예외발생 시 반환 메시지
        );
        memo.update(requestDto); // 메모 값 변경, requestDto 값으로 변경해줌
        return memo.getId();
    }

    // 메모 삭제하기
    @Transactional // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림(rollback)
    public Long deleteMemo(Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}