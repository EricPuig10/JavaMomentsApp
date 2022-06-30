package com.epapps.moments.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    private String description;

    private String ubication;

    //crear classe user, que tingui nom i avatar i enllaçar-ho aqui

    private String userName = "eric_puig";

    private boolean isLiked = false;

    private String userImg = "https://media-exp2.licdn.com/dms/image/C4D03AQG17WUfd78sgA/profile-displayphoto-shrink_400_400/0/1587477510501?e=1661385600&v=beta&t=lxPzlDjTbmYs0vSz-B7ef95gRfs-T5Dxj_RTLrcSO8Y";

    @OneToMany(mappedBy = "moment")
    private List<Comment> comments = new ArrayList<>();

    @JsonSerialize
    public int commentsCount(){
        return this.comments.size();
    }

    }

