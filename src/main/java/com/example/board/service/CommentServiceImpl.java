package com.example.board.service;

import com.example.board.dto.CreateCommentRequestDTO;
import com.example.board.dto.UpdateCommentRequestDTO;
import com.example.board.model.Comment;
import com.example.board.model.CommentStatus;
import com.example.board.model.Member;
import com.example.board.model.Post;
import com.example.board.model.PostStatus;
import com.example.board.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public Comment create(CreateCommentRequestDTO requestDTO) {
        Member member = memberService.findById(requestDTO.memberId());
        Post post = postService.findById(requestDTO.postId());
        String body = requestDTO.body();
        Comment comment = new Comment(member, post, body);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(UpdateCommentRequestDTO requestDTO) {
        Comment comment = this.findById(requestDTO.commentId());
        comment.setBody(requestDTO.body());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment setDelete(Long id) {
        Comment comment = this.findById(id);
        comment.setStatus(CommentStatus.DELETED);
        commentRepository.save(comment);
        return comment;
    }

}
