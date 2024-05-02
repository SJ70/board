package com.example.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public Member() {}

}
