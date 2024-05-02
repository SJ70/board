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
public class Reply {

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String body;

    private ReplyStatus status = ReplyStatus.ACTIVATED;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Reply(User user, Post post, String body) {
        this.user = user;
        this.post = post;
        this.body = body;
    }

    public Reply() {

    }

}
