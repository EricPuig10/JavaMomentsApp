package com.epapps.moments.mappers;

import com.epapps.moments.models.Fav;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;

public class FavMapper {
    public Fav mapReqToFav(User faver, Moment moment){
        Fav fav = new Fav();
        fav.setFaver(faver);
        fav.setMoment(moment);
        return fav;
    }
}
