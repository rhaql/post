package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;

    }


    public List<PostResponseDto> getPosts() {
        // DB 조회
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();

    }


    @Deprecated
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {

        // RequestDto -> Entity
        Post post = new Post(requestDto);

        post.setUser (user);
        // DB 저장)
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }


    // 선택 조회
    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto postrequestDto, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        post.setUser (user);
        // 비밀번호 체크
        post.checkPassword(postrequestDto.getPassword());
        // post 내용 수정
        post.update(postrequestDto);

        return id;

    }


    public Long deletePost(Long id, String passoword, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        post.setUser (user);
        // 비밀번호 체크
        post.checkPassword(passoword);

        // post 삭제
        postRepository.delete(post);

        return id;

    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다")
        );
    }

}

