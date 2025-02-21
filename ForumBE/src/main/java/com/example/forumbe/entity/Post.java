package com.example.forumbe.entity;

import com.example.forumbe.dto.PostDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "closedAt")
    private Date closedAt;

    @Column(name = "isOpen")
    private boolean isOpen;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostVote> postVotes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<Tag> tags = new ArrayList<>();

    public Post() {
    }

    public Post(String title, String body, Date createdAt, Date updatedAt, Date closedAt, boolean isOpen, User user, List<Tag> tags) {
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.isOpen = isOpen;
        this.user = user;
        this.tags = tags;
    }
}
