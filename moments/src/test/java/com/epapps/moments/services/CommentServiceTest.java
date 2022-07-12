package com.epapps.moments.services;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommentServiceTest {

    @Mock
    ICommentRepository commentRepository;
    IMomentsRepository momentsRepository;

    IUserRepository userRepository;
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
        var sut = commentRepository.findAll();
        assertThat(sut.size(), equalTo(3));
    }




}