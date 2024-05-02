package com.example.board.service;

import com.example.board.dto.CreatePostRequestDTO;
import com.example.board.model.Comment;
import com.example.board.model.Member;
import com.example.board.model.Post;
import com.example.board.model.PostStatus;
import com.example.board.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private MemberService memberService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    @Override
    @Transactional
    public List<Post> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public Post create(CreatePostRequestDTO requestDTO) {
        Member member = memberService.findById(requestDTO.memberId());
        Post post = new Post(member, requestDTO.title(), requestDTO.body());
        return postRepository.save(post);
    }

    @Override
    public void setDelete(Long id) {

    }
}
