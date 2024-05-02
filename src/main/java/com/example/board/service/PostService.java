package com.example.board.service;

import com.example.board.model.Post;
import java.util.List;

public interface PostService {

    List<Post> findAll();
    Post findById(Long id);
    Post save(Post post);
    void setDelete(Long id);

}
