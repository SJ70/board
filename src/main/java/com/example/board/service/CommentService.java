package com.example.board.service;

import com.example.board.dto.CreateCommentRequestDTO;
import com.example.board.dto.UpdateCommentRequestDTO;
import com.example.board.model.Comment;

public interface CommentService {

    Comment findById(Long id);
    Comment create(CreateCommentRequestDTO requestDTO);
    Comment update(UpdateCommentRequestDTO requestDTO);
    Comment setDelete(Long id);

}
