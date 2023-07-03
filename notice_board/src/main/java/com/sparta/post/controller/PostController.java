package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponseDto> getPosts() {

        return postService.getPosts();
    }

    @PostMapping
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postrequestDto) {

        return postService.createPost(postrequestDto, userDetails.getUser());
    }


    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {

        return postService.getPost(id);
    }

    @PutMapping("/{id}")
    public Long updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto, userDetails.getUser());

    }

    @DeleteMapping("/{id}")
    public Long deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.deletePost(id, postRequestDto, userDetails.getUser());

    }

}