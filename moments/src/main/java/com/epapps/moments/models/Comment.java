package com.epapps.moments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;
    private boolean isLiked = false;

    @ManyToOne
    @JoinColumn(name="creator_id")
    @JsonIgnore
    private User creator;

    @ManyToOne
    @JoinColumn(name = "moment_id")
    @JsonIgnore
    private Moment moment;


}
