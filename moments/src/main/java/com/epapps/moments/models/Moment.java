package com.epapps.moments.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Moments")
@NoArgsConstructor
@AllArgsConstructor
public class Moment {
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="imgUrl")
    private String imgUrl;

    @Column(nullable = false)
    //@NotNull
    private String description;

    private String ubication;

    private boolean isLiked = false;


    @OneToMany(mappedBy = "moment")
    private List<Comment> commentsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Moment(String title, Long id, String imgUrl, String ubication, String description, boolean isLiked) {

    }

    @JsonSerialize
    public int commentsCount(){
        return this.commentsList.size();
    }

    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;

}

