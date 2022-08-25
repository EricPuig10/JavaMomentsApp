package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
import com.epapps.moments.dtos.moment.MomentResDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.MomentMapper;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.IMomentsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
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
        var user = new User();
        user.setId(1L);
        var momentList = List.of(new Moment(), new Moment());
        momentList.forEach(Moment -> Moment.setCreator(user));
        Mockito.when(momentsRepository.findAll()).thenReturn(momentList);

        var sut = momentService.getAll(user);

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
        var moment = this.createMoment();
        var user = new User();
        user.setId(1L);
        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        var sut = momentService.findById(1L, user);
        assertThat(sut.getDescription(),  equalTo(moment.getDescription()));

    }


    @Test
    void deleteShouldReturnDeletedMoment() throws IOException {
        Long id = 1L;
        var momentService = new MomentService(momentsRepository);
        Moment moment = createMoment();
        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        var sut = momentService.deleteMoment(id, moment.getCreator());
        assertThat(sut.getDescription(), equalTo(moment.getDescription()));
    }

    @Test
    void deleteThrowsBadReqWhenUserIsntAuth(){
        var momentService = new MomentService(momentsRepository);
        var moment = createMoment();
        Mockito.when(momentsRepository.findById(1L)).thenReturn(Optional.ofNullable(moment));
        Exception ex = assertThrows(BadRequestException.class, ()->{
            momentService.deleteMoment(1L, new User());
        });
        var res = "Not user auth";
        var sut = ex.getMessage();
        assertTrue(sut.equals(res));
    }




    @Test
    void updateShouldUpdateMomentFromReq() throws IOException {
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
    void updateAMomentReturnsNullIfUserIsntAuthAndThrowsAnException() {
        var momentService = new MomentService(momentsRepository);
        var req = new MomentRequestDto("tit1", "img1", "desc1", "loc1", 1L);
        Long id = 1L;
        Moment moment = this.createMoment();
        var user = new User();
        user.setId(2L);
        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(moment));
        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(moment);
        Exception exception = assertThrows(BadRequestException.class, ()->{
            momentService.updateAMoment(req, id, user);
        });
        var res = "Moment can't be edited if you arent creator";
        var sut = exception.getMessage();
        assertTrue(sut.equals(res));

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
    void searchShouldReturnAListOfResMoments() {
        var momentService = new MomentService(momentsRepository);
        Moment moment = this.createMoment();
        var user = new User();
        user.setId(1L);
        MomentResDto res = new MomentMapper().mapToRes(moment, user);
        var filtered = List.of(moment);
        var foundMoments = List.of(res);
        Mockito.when(momentsRepository.findByDescriptionOrTitleContaining(any(String.class))).thenReturn(filtered);
        var sut = momentService.search("desc", user);
        assertThat(sut, equalTo(foundMoments));


    }


    /*@Test
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

    }*/






    }
