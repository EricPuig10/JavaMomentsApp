/*

package com.epapps.moments.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String username;
    private String img;
    private String password;
    private String email;
    private long followers;
    private long following;
    private String description;
    private String dateOfBirth;
    private String ubication;

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Moment> momentsList = new ArrayList<>();

    @JsonSerialize
    public int momentsCount(){
        return this.momentsList.size();
    }

    public void addMoment(Moment moment) {
        this.momentsList.add(moment);
    }

}
*/
