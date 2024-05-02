package com.example.board.controller;

import com.example.board.dto.CreateCommentRequestDTO;
import com.example.board.dto.UpdateCommentRequestDTO;
import com.example.board.model.Comment;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequestDTO requestDTO) {
        Comment comment = commentService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PatchMapping
    public ResponseEntity<Comment> updateComment(@RequestBody UpdateCommentRequestDTO requestDTO) {
        Comment comment = commentService.update(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

}
