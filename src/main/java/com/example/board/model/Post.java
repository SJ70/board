package com.example.board.model;

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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "reply_id")
    private List<Reply> replies = new ArrayList<>();

    private String title;
    private String body;

    private PostStatus status = PostStatus.ACTIVATED;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
    }

    public Post() {
    }
}