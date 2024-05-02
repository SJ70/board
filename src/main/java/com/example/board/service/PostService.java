package com.example.board.service;

import com.example.board.dto.CreatePostRequestDTO;
import com.example.board.dto.PostAndCommentsDTO;
import com.example.board.dto.UpdatePostRequestDTO;
import com.example.board.model.Post;
import java.util.List;

public interface PostService {

    List<Post> findAll();
    PostAndCommentsDTO lookUp(Long id);
    Post findById(Long id);
    Post create(CreatePostRequestDTO requestDTO);
    Post update(UpdatePostRequestDTO requestDTO);
    Post setDelete(Long id);

}
