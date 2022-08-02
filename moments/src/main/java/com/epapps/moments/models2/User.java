package com.epapps.moments.models2;

import com.epapps.moments.models.Moment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String img;
    private String username;
    private long followers;
    private long following;
    private String description;
    private String dateOfBirth;
    private String ubication;

    private String email;
    @JsonIgnore
    private String password;
    public User(String name) {
        this.username = name;
    }

    @ManyToMany
    private Set<Role> roles;


    public User(long id, String name) {
        this.username = name;
        this.id = id;
    }

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }

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
