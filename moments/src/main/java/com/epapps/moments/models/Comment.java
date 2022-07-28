package com.epapps.moments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name="creator_id")
    @JsonIgnore
    private User creator;

    @ManyToOne
    @JoinColumn(name = "moment_id")
    @JsonIgnore
    private Moment moment;

    @OneToMany(mappedBy = "comment")
    @JsonIgnore
    private List<Fav> favComments =  new ArrayList<>();
    public void toggleFav(Fav fav){
        if(!fav.getComment().equals(this)) return;
        var found = favComments.stream().filter(Fav -> Fav.getFaver() == fav.getFaver()).findAny();
        if (found.isPresent()) {
            favComments.remove(found.get());
            return;
        }
        favComments.add(fav);
    }

    @JsonSerialize
    public int favsCount() {
        return this.favComments.size();
    }


    public boolean isFaved(User user) {

        var faver = favComments.stream().filter(Fav -> Fav.getFaver().getId() == user.getId()).findAny();
        if(faver.isEmpty()) {
            return false;
        }
        return true;
    }


}
