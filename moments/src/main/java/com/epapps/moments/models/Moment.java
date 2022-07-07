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
    @NotNull
    private String description;

    private String ubication;

    private boolean isLiked = false;




    @OneToMany(mappedBy = "moment")
    private List<Comment> comments = new ArrayList<>();


    @JsonSerialize
    public int commentsCount(){
        return this.comments.size();
    }

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;
    }

