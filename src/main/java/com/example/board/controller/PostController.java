package com.example.board.controller;

import com.example.board.dto.CreatePostRequestDTO;
import com.example.board.dto.PostAndCommentsDTO;
import com.example.board.dto.PostPreviewDTO;
import com.example.board.dto.UpdatePostRequestDTO;
import com.example.board.model.Post;
import com.example.board.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<Page<PostPreviewDTO>> previewPosts(Pageable pageable) {
        return ResponseEntity.ok(postService.previewPosts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostAndCommentsDTO> lookUpPost(@PathVariable Long id) {
        PostAndCommentsDTO responseDTO = postService.lookUp(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequestDTO requestDTO) {
        Post post = postService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PatchMapping
    public ResponseEntity<Post> updatePost(@RequestBody UpdatePostRequestDTO requestDTO) {
        try {
            Post post = postService.update(requestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(post);
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Post post = postService.setDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

}
