package com.epapps.moments.services;

import com.epapps.moments.dtos.fav.FavCommentReqDto;
import com.epapps.moments.dtos.fav.FavReqDto;
import com.epapps.moments.dtos.fav.FavResDto;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models2.User;

import java.util.List;

public interface IFavService {
    List<FavResDto> getAll();
    List<FavResDto> getMomentFavs(Long id);


    boolean toggleFav(FavReqDto fav);

    //boolean toggleFavComment(FavCommentReqDto fav, User auth);
}
