package com.example.board.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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
