package com.epapps.moments.services;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.dtos.comment.CommentResDto;
import com.epapps.moments.mappers.CommentMapper;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class CommentServiceTest {

    @Mock
    ICommentRepository commentRepository;
    IMomentsRepository momentsRepository;

    IUserRepository userRepository;
    private Moment createMoment(){
        var creator = new User();
        creator.setId(1L);
        var moment =  new Moment();
        moment.setId(1L);
        moment.setCreator(creator);
        return moment;
    }

    private User createUser(){
        User user = new User();
        user.setId(2L);
        return user;
    }
    Comment createComment (Moment moment){
        User creator = new User();
        creator.setId(2L);
        Comment comment = new Comment();
        comment.setCreator(creator);
        comment.setMoment(moment);
        return comment;
    }
    @Test
    void findAllShouldReturnAllComents() {
        var commentService = new CommentService(commentRepository, momentsRepository, userRepository);
        Moment moment = new Moment();
        User creator = new User();
        List<Comment> listOfComments = List.of(new Comment(), new Comment(), new Comment());
        listOfComments.forEach(Comment -> {
            Comment.setMoment(moment);
            Comment.setCreator(creator);
        });
        Mockito.when(commentRepository.findAll()).thenReturn(listOfComments);
        var sut = commentService.getAll();
        assertThat(sut.size(), equalTo(3));
    }

    @Test
    void findByIdShouldReturnAComment(){
        var commentService = new CommentService(commentRepository, momentsRepository, userRepository);

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setComment("comment");
        Long id = 1L;

        Mockito.when(commentRepository.findById(id)).thenReturn(Optional.of(comment));

        var sut = commentService.findById(id);

        assertThat(sut.getComment(), equalTo(comment.getComment()));

    }

    @Test
    void findByMomentShouldReturnAListOfComments(){

        var commentService = new CommentService(commentRepository, momentsRepository, userRepository);
        Moment moment = this.createMoment();
        Comment comment = this.createComment(moment);
        List<Comment> comments = List.of(comment, comment, comment);
        Mockito.when(commentRepository.findByMomentId(any(Long.class))).thenReturn(comments);
        var sut = commentService.findByMoment(1L);
        assertThat(sut.get(0).getMomentId(), equalTo(1L));


    }





}