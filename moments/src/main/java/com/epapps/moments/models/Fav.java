package com.epapps.moments.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="favs")
@NoArgsConstructor

public class Fav {

    public Fav(User faver, Moment moment) {
        this.faver = faver;
        this.moment = moment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="faver_id")
    private User faver;

    @ManyToOne
    @JoinColumn(name="moment_id")
    private Moment moment;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;
}
