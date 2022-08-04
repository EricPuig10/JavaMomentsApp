package com.epapps.moments.mappers;

import com.epapps.moments.dtos.fav.FavResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;

import java.util.ArrayList;
import java.util.List;

public class FavMapper {
    public Fav mapReqToFav(User faver, Moment moment){
        Fav fav = new Fav();
        fav.setFaver(faver);
        fav.setMoment(moment);
        return fav;
    }

    public Fav mapCommentReqToFav(User faver, Comment comment){
        Fav fav = new Fav();
        fav.setFaver(faver);
        fav.setComment(comment);
        return fav;
    }

    public FavResDto mapFavToFavResDto(Fav fav){
        return new FavResDto(fav.getId(), fav.getMoment().getId(), fav.getFaver().getId());
    }

    public List<FavResDto> mapMultipleFavsToFavsDto(List<Fav> favs){
        List<FavResDto> res = new ArrayList<>();
        favs.forEach(Like-> res.add(this.mapFavToFavResDto(Like)));
        return res;
    }
}
