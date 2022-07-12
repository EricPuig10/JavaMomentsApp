package com.epapps.moments.services;

import com.epapps.moments.dtos.CommentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;

import java.util.List;

public interface ICommentService {
    List<Comment> getAll();

    Comment findById(Long id);

    Comment create(CommentRequestDto commentDto, User userAuth);

    Comment updateComment(Comment comment, User auth);

    List<Comment> findByMoment(Long id);

    Comment like(Long id, Comment comment);
}
