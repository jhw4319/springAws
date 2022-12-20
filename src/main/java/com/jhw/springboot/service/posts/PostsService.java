package com.jhw.springboot.service.posts;

import com.jhw.springboot.domain.posts.Posts;
import com.jhw.springboot.domain.posts.PostsRepository;
import com.jhw.springboot.web.dto.PostsResponseDto;
import com.jhw.springboot.web.dto.PostsSaveRequestDto;
import com.jhw.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional //  메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
    public Long save (PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public Long update (Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }


}
