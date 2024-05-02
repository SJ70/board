package com.example.board.service;

import com.example.board.dto.CreateCommentRequestDTO;
import com.example.board.model.Comment;
import com.example.board.model.Member;
import com.example.board.model.Post;
import com.example.board.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private MemberService memberService;
    private PostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, MemberService memberService, PostService postService) {
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Override
    @Transactional
    public Comment create(CreateCommentRequestDTO requestDTO) {
        System.out.println(requestDTO.memberId());
        Member member = memberService.findById(requestDTO.memberId());
        Post post = postService.findById(requestDTO.postId());
        String body = requestDTO.body();
        Comment comment = new Comment(member, post, body);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment setDelete(Long id) {
        return null;
    }

}
