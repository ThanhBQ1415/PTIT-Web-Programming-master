package com.example.forumbe.entity;

import com.example.forumbe.dto.PostVoteDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_votes")
@Getter
@Setter
public class PostVote {
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
    private Post post;

    public PostVote() {
    }

    public PostVote(boolean isUpVote, Date updatedAt, User user, Post post) {
        this.isUpVote = isUpVote;
        this.updatedAt = updatedAt;
        this.user = user;
        this.post = post;
    }

}
