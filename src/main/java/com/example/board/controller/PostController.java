package com.example.board.controller;

import com.example.board.dto.CreatePostRequestDTO;
import com.example.board.model.Member;
import com.example.board.model.Post;
import com.example.board.service.MemberService;
import com.example.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private MemberService memberService;

    @Autowired
    public PostController(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequestDTO requestDTO) {
        Member author = memberService.findById(requestDTO.memberId());
        Post createdPost = new Post(author, requestDTO.title(), requestDTO.body());
        postService.save(createdPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

}
