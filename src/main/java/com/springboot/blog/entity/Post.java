package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", unique = true, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name="content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();
}
