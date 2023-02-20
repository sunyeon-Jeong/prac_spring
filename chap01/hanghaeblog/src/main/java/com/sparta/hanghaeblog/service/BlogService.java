package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.BlogRequestDto;
import com.sparta.hanghaeblog.dto.BlogResponseDto;
import com.sparta.hanghaeblog.entity.Blog;
import com.sparta.hanghaeblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 서비스단임을 알림
@RequiredArgsConstructor // final, @Notnull -> 필드의 생성자 자동생성
public class BlogService {
    private final BlogRepository blogRepository; // DB 연결하는 레포지토리에 연결

    // 게시글작성
    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // 반환 -> Entity Blog 클래스가 아닌, Dto 클래스
        /* Blog blog = new Blog(requestDto) -> 원래 Entity로 반환해야함*/
        Blog blog = Blog.builder()
                .requestDto(requestDto)
                .build();
        blogRepository.save(blog); // requestDto를 담은 blog 객체를 DB에 저장
        return new BlogResponseDto(blog); // 저장된 게시글 -> entity가 아닌 responseDto로 변환
    }
}
