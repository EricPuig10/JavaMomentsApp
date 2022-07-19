package com.epapps.moments.services;

import com.epapps.moments.dtos.fav.FavReqDto;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models.User;

import java.util.List;

public interface IFavService {
    List<Fav> getAll();
    List<Fav> getMomentFavs(Long id);


    String toggleFav(FavReqDto fav, User auth);
}
