package com.epapps.moments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    //private boolean isLiked = false;

    //private int likes;

    @OneToMany(mappedBy = "moment")
    @JsonIgnore
    private List<Comment> commentsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;


    public Moment(String title, Long id, String imgUrl, String ubication, String description, boolean isLiked) {

    }

    @JsonSerialize
    public int commentsCount(){
        return this.commentsList.size();
    }

    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }

    @OneToMany(mappedBy = "moment")
    @JsonIgnore
    private List<Fav> favs =  new ArrayList<>();
    public void toggleFav(Fav fav){
        if(!fav.getMoment().equals(this)) return;
        var found = favs.stream().filter(Fav -> Fav.getFaver() == fav.getFaver()).findAny();
        if (found.isPresent()) {
            favs.remove(found);
            return;
        }
        favs.add(fav);
    }

    public int favsCount() {
        return this.favs.size();
    }

    public boolean isFaved(User user) {

        var faver = favs.stream().filter(Fav -> Fav.getFaver() == user).findAny();
        if(faver.isEmpty()) {
            return false;
        }
        return true;
    }
}


