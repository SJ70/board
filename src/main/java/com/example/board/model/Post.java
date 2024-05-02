package com.example.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private String body;

    private PostStatus status = PostStatus.ACTIVATED;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post(Member member, String title, String body) {
        this.member = member;
        this.title = title;
        this.body = body;
    }

    public Post() {
    }

    public boolean isDeleted() {
        return this.status == PostStatus.DELETED;
    }

}
