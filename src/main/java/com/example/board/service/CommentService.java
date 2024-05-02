package com.example.board.service;

import com.example.board.dto.CreateCommentRequestDTO;
import com.example.board.model.Comment;

public interface CommentService {

    Comment create(CreateCommentRequestDTO requestDTO);
    Comment setDelete(Long id);

}
