package com.example.forumbe.entity;

import com.example.forumbe.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "aboutMe")
    private String aboutMe;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostVote> postVotes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommentVote> commentVotes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String aboutMe, String avatar, boolean isActive, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.aboutMe = aboutMe;
        this.avatar = avatar;
        this.isActive = isActive;
        this.roles = roles;
    }
}
