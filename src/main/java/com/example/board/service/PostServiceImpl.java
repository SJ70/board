package com.example.board.service;

import com.example.board.model.Post;
import com.example.board.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public List<Post> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void setDelete(Long id) {

    }
}
