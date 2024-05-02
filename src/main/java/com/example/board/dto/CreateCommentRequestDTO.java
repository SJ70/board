package com.example.board.dto;

public record CreateCommentRequestDTO(Long memberId, Long postId, String body) {

}
