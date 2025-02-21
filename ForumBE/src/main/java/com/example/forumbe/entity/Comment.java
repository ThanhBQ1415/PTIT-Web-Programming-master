package com.example.forumbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    @OneToMany(mappedBy = "comment")
    private List<CommentVote> commentVotes = new ArrayList<>();


    public Comment() {
    }

    public Comment(String body, Date createdAt, User user, Post post) {
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
        this.post = post;
    }
}
