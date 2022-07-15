package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentRequestDto;
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
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MomentServiceTest {

    @Mock

    IMomentsRepository momentsRepository;

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

        var momentRequestDto = new MomentRequestDto("hola", "hola.jpg", "hola", "main");
        User creator = new User();
        creator.setId(1L);

        Moment moment = new Moment();
        moment.setDescription("hola");
        moment.setCreator(creator);
        moment.setUbication("home");
        moment.setTitle("hola");
        moment.setImgUrl("hola.jpg");
        moment.setId(1L);
        moment.setLiked(true);

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
    void updateAMomentReturnsAMomentUpdated() {
        var momentService = new MomentService(momentsRepository);

        User creator = new User();
        creator.setId(1L);



        Moment momentToEdit = new Moment("hola", 1L, "hola.jpg", "hola", "hola", true );
        Long id = 1L;
        momentToEdit.setCreator(creator);


        Mockito.when(momentsRepository.findById(momentToEdit.getId())).thenReturn(Optional.of(momentToEdit));

        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(momentToEdit);


        var sut = momentService.updateAMoment(momentToEdit, creator);

        assertThat (sut.getTitle(), equalTo(momentToEdit.getTitle()));

    }


    @Test
    void updateAMomentReturnsNullIfUserIsntAuth() {
        var momentService = new MomentService(momentsRepository);

        User creator = new User();
        creator.setId(1L);

        User notcreator = new User();
        notcreator.setId(2L);

        Moment momentToEdit = new Moment();
        momentToEdit.setId(1L);
        momentToEdit.setDescription("hello");
        momentToEdit.setTitle("title");
        momentToEdit.setCreator(creator);

        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.of(momentToEdit));

        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(momentToEdit);


        var sut = momentService.updateAMoment(momentToEdit, notcreator);

        assertThat(sut, equalTo(null));

    }

    /*
    @Test
    void updateAMomentReturnsNullIfDontExistMoment() {
        var momentService = new MomentService(momentsRepository);

        User creator = new User();
        creator.setId(1L);

        Moment momentToEdit = new Moment();

        Mockito.when(momentsRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Mockito.when(momentsRepository.save(any(Moment.class))).thenReturn(null);


        var sut = momentService.updateAMoment(momentToEdit, creator);

        assertThat(sut.getTitle(), equalTo(null));

    } */

    //si no existeix moment, null




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

        Mockito.when(momentsRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(any(String.class), any(String.class))).thenReturn(searched);

        var sut = momentService.search("title");

        assertThat(sut, equalTo(found));


    }
}