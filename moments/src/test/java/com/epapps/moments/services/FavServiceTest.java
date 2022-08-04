package com.epapps.moments.services;

import com.epapps.moments.auth.facade.IAuthenticationFacade;
import com.epapps.moments.models.Fav;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IFavRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FavServiceTest {
    @Mock
    IMomentsRepository momentsRepository;
    @Mock
    IUserRepository userRepository;
    @Mock
    IFavRepository favRepository;
    @Mock
    IAuthenticationFacade authenticationFacade;




    @Test
    void getAllShouldReturnListOfLikes() {
        int n = 3;
        var favService = new FavService(favRepository, momentsRepository, authenticationFacade);
        List<Fav> favs = this.createFavs(n);
        Mockito.when(favRepository.findAll()).thenReturn(favs);
        var sut = favService.getAll();
        assertThat(sut.size(), equalTo(n));

    }

    @Test
    void getMomentFavs() {

    }

    @Test
    void toggleFav() {
    }

    private List<Fav> createFavs(int n){
        var moment = this.createMoment();
        List<Fav> favs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            var user = new User();
            user.setId(Long.valueOf(i));
            var fav = new Fav(user, moment);
            favs.add(fav);
        }
        return favs;
    }

    public Moment createMoment(){
        var user = new User();
        user.setId(1L);
        var moment = new Moment();
        moment.setId(1L);
        moment.setTitle("tit1");
        moment.setDescription("des1");
        moment.setImgUrl("img1");
        moment.setUbication("ubi1");
        moment.setCreator(user);
        return moment;
    }
}