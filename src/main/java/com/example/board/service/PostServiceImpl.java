package com.example.board.service;

import com.example.board.dto.CreatePostRequestDTO;
import com.example.board.dto.PostAndCommentsDTO;
import com.example.board.dto.PostPreviewDTO;
import com.example.board.dto.UpdatePostRequestDTO;
import com.example.board.model.Comment;
import com.example.board.model.CommentStatus;
import com.example.board.model.Member;
import com.example.board.model.Post;
import com.example.board.model.PostStatus;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private MemberService memberService;
    private CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, MemberService memberService, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.memberService = memberService;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Page<PostPreviewDTO> previewPosts(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.map(PostPreviewDTO::new);
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    @Transactional
    public PostAndCommentsDTO lookUp(Long id) {
        Post post = this.findById(id);
        List<Comment> comments = post.getComments()
                .stream()
                .filter(c -> !c.isDeleted())
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .toList();
        return new PostAndCommentsDTO(post, comments);
    }

    @Override
    @Transactional
    public Post create(CreatePostRequestDTO requestDTO) {
        Member member = memberService.findById(requestDTO.memberId());
        Post post = new Post(member, requestDTO.title(), requestDTO.body());
        return postRepository.save(post);
    }

    @Override
    public Post update(UpdatePostRequestDTO requestDTO) {
        Post post = this.findById(requestDTO.postId());
        if (post.isDeleted()) {
            throw new IllegalStateException("삭제된 게시글은 수정할 수 없습니다.");
        }
        post.setTitle(requestDTO.title());
        post.setBody(requestDTO.body());
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post setDelete(Long id) {
        Post post = this.findById(id);
        post.setStatus(PostStatus.DELETED);
        postRepository.save(post);

        for (Comment comment : post.getComments()) {
            comment.setStatus(CommentStatus.DELETED);
            commentRepository.save(comment);
        }
        return post;
    }
}
