package com.example.board.dto;

import com.example.board.model.Post;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostPreviewDTO {

    private Long id;
    private String title;
    private Long memberId;
    private String memberName;
    private LocalDateTime createdAt;

    public PostPreviewDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.memberId = post.getMember().getId();
        this.memberName = post.getMember().getName();
        this.createdAt = post.getCreatedAt();
    }

}
