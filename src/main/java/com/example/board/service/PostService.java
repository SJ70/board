package com.example.board.service;

import com.example.board.dto.CreatePostRequestDTO;
import com.example.board.dto.PostAndCommentsDTO;
import com.example.board.dto.PostPreviewDTO;
import com.example.board.dto.UpdatePostRequestDTO;
import com.example.board.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<PostPreviewDTO> previewPosts(Pageable pageable);
    PostAndCommentsDTO lookUp(Long id);
    Post findById(Long id);
    Post create(CreatePostRequestDTO requestDTO);
    Post update(UpdatePostRequestDTO requestDTO);
    Post setDelete(Long id);

}
