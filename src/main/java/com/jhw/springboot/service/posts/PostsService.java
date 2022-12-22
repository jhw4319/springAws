package com.jhw.springboot.service.posts;

import com.jhw.springboot.domain.posts.Posts;
import com.jhw.springboot.domain.posts.PostsRepository;
import com.jhw.springboot.web.dto.PostsListResponseDto;
import com.jhw.springboot.web.dto.PostsResponseDto;
import com.jhw.springboot.web.dto.PostsSaveRequestDto;
import com.jhw.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회기능만 남겨두므로 조회속도가 개선됨
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }


}
