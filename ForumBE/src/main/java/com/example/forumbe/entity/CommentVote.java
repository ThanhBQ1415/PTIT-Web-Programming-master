package com.example.forumbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment_votes")
@Getter
@Setter
public class CommentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isUpVote")
    private boolean isUpVote;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

    public CommentVote() {
    }

    public CommentVote(boolean isUpVote, Date updatedAt, User user, Comment comment) {
        this.isUpVote = isUpVote;
        this.updatedAt = updatedAt;
        this.user = user;
        this.comment = comment;
    }
}
