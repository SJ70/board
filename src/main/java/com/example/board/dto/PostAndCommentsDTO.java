package com.example.board.dto;

import com.example.board.model.Comment;
import com.example.board.model.Post;
import java.util.Comparator;
import java.util.List;

public record PostAndCommentsDTO(Post post, List<Comment> comments) {

}
