package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.BlogRequestDto;
import com.sparta.hanghaeblog.dto.BlogResponseDto;
import com.sparta.hanghaeblog.entity.Blog;
import com.sparta.hanghaeblog.service.BlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController // Json 형태의 데이터 반환
@RequiredArgsConstructor // final, @Notnull -> 필드의 생성자 자동생성
public class BlogController {
    // DB연결
    private final BlogService blogService;

    // 게시글작성
    @PostMapping("/api/posts") // 생성 -> POST 형식
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        // 느슨한결합 -> Entity 바로 반환하지 않고, DTO에 담아서 반환
        // Entity인 Memo가 반환타입 X -> BlogResponseDto가 반환타입!
        // @RequestBody : Client 입력값 -> HTTP BODY에 Json형태로 넘어감 -> 메서드 파라미터에 값을 받아올 객체를 지정
        return blogService.createBlog(requestDto); // Service단에서 createBlog메서드 리턴 (requestDto 파라미터로 받음)
    }

    // 전체게시글목록조회
    @GetMapping("/api/posts") // 조회 -> GET 형식
    public List<BlogResponseDto> getBlog() { // 반환타입은 ResponseDto(Service단에서 그렇게 지정함)
        return blogService.getBlog(); // Service단에서 getBlog메서드 리턴
    }

    // 선택한게시글조회
    @GetMapping("/api/post/{id}") // 조회 -> GET 형식
    public BlogResponseDto getSelectedBlog(@PathVariable Long id) {
        // 느슨한결합 -> Entity 바로 반환하지 않고, DTO에 담아서 반환
        // @PathVariable : URL을 통해 전달된 값 -> 파라미터로 받아옴
        return blogService.getSelectedBlog(id);
        // Service단의 getSelectedBlog 메서드에 id값 넣어 return
    }

    // 선택한게시글수정
    @PutMapping("api/post/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        // 느슨한결합 -> Entity 바로 반환하지 않고, DTO에 담아서 반환
        // @RequestBody : Client 입력값 -> HTTP BODY에 Json형태로 넘어감 -> 메서드 파라미터에 값을 받아올 객체를 지정
        // @PathVariable : URL을 통해 전달된 값 -> 파라미터로 받아옴
        return blogService.updateBlog(id, requestDto);
        // Service단의 updateBlog 메서드 return (id, requestDto 파라미터로 받음)
    }

    // 선택한게시글삭제
    @DeleteMapping("api/post/{id}")
    public Map<String, String> deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto, HttpServletResponse res) {
        // @PathVariable : URL을 통해 전달된 값 -> 파라미터로 받아옴
        // @RequestBody : Client 입력값 -> HTTP BODY에 Json형태로 넘어감 -> 메서드 파라미터에 값을 받아올 객체를 지정
        // HttpServletResponse : HttpServletResponse 객체 res에 응답 코드 or 메시지를 담아 전송
        try { // 삭제 성공 메시지 or 실패 메시지 반환을 위한 예외처리
            BlogService.deleteBlog(id, requestDto);
            // id, requestDto의 수정값 이용 -> Service단에서 deleteBlog 메서드 실행
            statusMsg.put("msg", "삭제완료!");
            // 성공 -> statusMsg에 성공메시지 넣기(HashMap 사용했기 때문에, key/value 값으로 파라미터에 넣어주기)
        } catch (IllegalStateException | IllegalArgumentException e) {
            // 부적절할때 메서드 호출 or 잘못된 인수 넘겨준 예외 발생 시, 객체 e로 받음
            res.setStatus(HttpStatus.BAD_REQUEST.value());
            // res 객체 <- error 상태메시지 삽입
            statusMsg.put("msg", e.getMessage());
            // 삭제요청 실패 시 -> statusMsg에 예외 객체로부터 실패메시지 넣기(HashMap 사용했기 때문에, key/value 값으로 파라미터에 넣어주기)
        }
        return statusMsg;
    }
}