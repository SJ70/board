package com.example.board.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    @OneToMany(mappedBy = "post")
    private List<Post> posts = new ArrayList<>();

    public User() {}

}
