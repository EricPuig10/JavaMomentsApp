package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.MomentMapper;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.IMomentsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MomentServiceTest {

    @Mock

    IMomentsRepository momentsRepository;

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

    @Test
    void getAllReturnsListOfProducts() {
        var momentService = new MomentService(momentsRepository);

        var momentList = List.of(new Moment(), new Moment());

        Mockito.when(momentsRepository.findAll()).thenReturn(momentList);

        var sut = momentService.getAll();

        assertThat (sut.size(), equalTo(2));
    }


    @Test
    void createSaveAMoment() {
        MomentService momentService = new MomentService(momentsRepository);

        var momentRequestDto = new MomentRequestDto("hola", "hola.jpg", "hola", "main", 1L);
        User creator = new User();
        creator.setId(1L);

        Moment moment = new Moment();
        moment.setDescription("hola");
        moment.setCreator(creator);
        moment.setUbication("home");
        moment.setTitle("hola");
        moment.setImgUrl("hola.jpg");
        moment.setId(1L);


        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(moment);
        var sut = momentService.create(momentRequestDto, creator);

        assertThat(sut.getDescription(), equalTo(moment.getDescription()));
        assertThat(sut.getCreator(), equalTo(creator));



    }

    @Test
    void findByIdReturnsAnIdIfExists() {

        var momentService = new MomentService(momentsRepository);

        var moment = new Moment();
        moment.setId(1L);


        Mockito.when(momentsRepository.findById(1L)).thenReturn(Optional.of(moment));

        Long id = 1L;
        var sut = momentService.findById(id);

        assertThat (sut.getId(), equalTo(moment.getId()));

    }


    @Test
    void deleteMoment() {
        var momentService = new MomentService(momentsRepository);

        User creator = new User();
        creator.setId(1L);


        var moment = new Moment();
        moment.setId(1L);
        moment.setTitle("moment amb id 1");
        moment.setCreator(creator);

        Long id = 1L;

        var momentList = List.of(moment, new Moment());

        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));


        var sut = momentService.deleteMoment(1L, creator);

        assertThat(sut, equalTo(true));

    }


    @Test
    void momentCantBeDeletedForAUserThatIsntAuth(){
        var momentService = new MomentService(momentsRepository);

        User creator = new User();
        creator.setId(1L);

        User notcreator =  new User();
        notcreator.setId(2L);


        var moment = new Moment();
        moment.setId(1L);
        moment.setTitle("moment amb id 1");
        moment.setCreator(notcreator);

        Long id = 1L;

        var momentList = List.of(moment, new Moment());

        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));


        var sut = momentService.deleteMoment(1L, creator);

        assertThat(sut, equalTo(null));

    }




    @Test
    void updateShouldUpdateMomentFromReq() {
        var momentService = new MomentService(momentsRepository);
        var req = new MomentRequestDto("tit1", "img1", "des1", "ubi1", 1L);
        Long id = 1L;

        var user = new User();
        user.setId(1L);

        Moment moment = this.createMoment();

        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.updateAMoment(req, id, user);
        assertThat(sut.getDescription(), equalTo(req.getDescription()));
        assertThat(sut.getTitle(), equalTo(req.getTitle()));
        assertThat(sut.getImgUrl(), equalTo(req.getImgUrl()));
        assertThat(sut.getUbication(), equalTo(req.getUbication()));
        assertThat(sut.getCreator().getId(), equalTo(req.getUserId()));
    }



    @Test
    void updateAMomentReturnsNullIfUserIsntAuth() {
        var momentService = new MomentService(momentsRepository);
        var req = new MomentRequestDto("tit1", "img1", "des1", "ubi1", 1L);
        Long id = 1L;

        var user = new User();
        user.setId(1L);
        var userNonAuth = new User();
        user.setId(3L);

        Moment moment = this.createMoment();

        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(moment);

        var sut = momentService.updateAMoment(req, id, userNonAuth);

        assertThat(sut, equalTo(null));

    }



    @Test
    void updateAMomentReturnsNotFoundExceotionIfDontExistMoment() {

        var momentService = new MomentService(momentsRepository);
        var req = new MomentRequestDto("img", "desc", "loc", "ubi", 1L);
        var user = new User();
        user.setId(1L);
        Exception ex = assertThrows(NotFoundException.class, ()->{
            momentService.updateAMoment(req, 1L, user);
        });
        var resmsg = "Moment Not Found";
        var sut = ex.getMessage();
        assertTrue(sut.equals(resmsg));


    }






    @Test
    void searchShouldReturnAListOfMoments() {
        var momentService = new MomentService(momentsRepository);
        Moment moment = new Moment();
        moment.setTitle("title");
        moment.setDescription("desc");
        moment.setUbication("ubi");
        moment.setImgUrl("img");

        Moment moment2 = new Moment();
        moment2.setTitle(moment.getTitle());
        moment2.setDescription(moment.getDescription());
        moment2.setImgUrl(moment.getImgUrl());
        moment2.setUbication(moment.getUbication());

        var searched = List.of(moment);

        var found = List.of(moment2);

        Mockito.when(momentsRepository.findByDescriptionOrTitleContaining(any(String.class))).thenReturn(searched);

        var sut = momentService.search("title");

        assertThat(sut, equalTo(found));


    }


    @Test
    void getUserMomentsShouldReturnMomentList() {
        var momentService = new MomentService(momentsRepository);
        Moment moment = this.createMoment();
        var user = new User();
        user.setId(1L);
        Moment res = this.createMoment();
        var filtered = List.of(moment);
        var foundMoments = List.of(res);
        Mockito.when(momentsRepository.getMomentsByUserId(any(Long.class))).thenReturn(filtered);
        var sut = momentService.findByUserMoments(1L);
        assertThat(sut, equalTo(foundMoments));

    }






    }
