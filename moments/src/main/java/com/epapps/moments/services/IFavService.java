package com.epapps.moments.services;

import com.epapps.moments.dtos.fav.FavCommentReqDto;
import com.epapps.moments.dtos.fav.FavReqDto;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models2.User;

import java.util.List;

public interface IFavService {
    List<Fav> getAll();
    List<Fav> getMomentFavs(Long id);


    boolean toggleFav(FavReqDto fav, User auth);

    //boolean toggleFavComment(FavCommentReqDto fav, User auth);
}
