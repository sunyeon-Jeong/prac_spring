package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.BlogRequestDto;
import com.sparta.hanghaeblog.dto.BlogResponseDto;
import com.sparta.hanghaeblog.entity.Blog;
import com.sparta.hanghaeblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // 서비스단임을 알림 (@Transactional을 꼭 붙여주자!! rollback을 위해!)
@RequiredArgsConstructor // final, @Notnull -> 필드의 생성자 자동생성
public class BlogService {
    private final BlogRepository blogRepository; // DB 연결하는 레포지토리에 연결

    // 게시글작성
    @Transactional // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // 반환 -> Entity Blog 클래스가 아닌, Dto 클래스
        /* Blog blog = new Blog(requestDto) -> 원래 Entity로 반환해야함*/
        Blog blog = Blog.builder() // Entity Blog 클래스의 객체 생성
                .requestDto(requestDto) // Entity Blog의 생성자에 파라미터(참조)로 들어감
                .build();
        blogRepository.save(blog); // requestDto를 담은 blog 객체를 DB에 저장
        return new BlogResponseDto(blog); // 저장된 게시글 -> entity가 아닌 responseDto로 변환
    }

    // 전체게시글목록조회
    @Transactional(readOnly = true) // DB 작업 처리 중, 오류발생 -> 모든 작업 원상태로 되돌림
    public List<BlogResponseDto> getBlog() {
        // (조회)이기 때문에, 파라미터에 Client로부터 받아야 할 데이터 없음
        // 반환 -> ResponseDto 클래스 List 형태
        List<Blog> blogList = blogRepository.findAllByOrderByModifiedAtDesc();
        // Entity Blog 클래스의 List형태 객체 생성
        // 수정시간 내림차순으로 모든 게시글 DB에서 가져와서 조회
        List<BlogResponseDto> responseBlogList = new ArrayList<>();
        // (BlogRequestDto만 들어올 수 있는)크기를 조절할 수 있는 배열
        // ResponseDto 리스트 형태 반환타입인 responseBlogList 객체 만듦
        for (Blog blog : blogList) { // 리스트에서 리스트로 바로 데이터 못 옮김
            // 내림차순으로 DB에서 가져온 모든 게시글 -> 하나씩 Entity Blog 클래스에 대입
            responseBlogList.add(new BlogResponseDto(blog));
            // ResponseDto의 ArrayList 객체에 <- DB에서 보냈던 Entity Class blog 데이터 하나씩 add
        }
        return responseBlogList; // Client단에 return
    }

    // 선택한게시글조회
    @Transactional(readOnly = true)
    public BlogResponseDto getSelectedBlog(Long id) {
        // Controller 단에서 Service 호출할때, 파라미터로 받은 id값으로 -> 해당 게시글 찾음
        Blog selectedBlog = blogRepository.findById(id).orElseThrow(
                // Entity Blog Class의 selectedBlog 객체 생성
                // DB에서 findById(id)작업하고, id가 없으면 예외로 던짐
                () -> new IllegalStateException("해당 게시글이 없습니다.")
                // IllegalStateException : 적절하지 못한 인자를 메서드로 넘겨주었을 때의 예외
        );
        return new BlogResponseDto(selectedBlog);
    }
}

/* 주요 Entity Class의 객체를 만들어
RequestDto 값 가져오고, Repository(DB)에 값 추가/수정/삭제 등 작업.
ResponseDto에 작업한 값 return
 */