package com.epapps.moments.services;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.dtos.comment.CommentResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models2.User;

import java.util.List;

public interface ICommentService {
    List<CommentResDto> getAll();

    Comment findById(Long id);

    CommentResDto create(CommentRequestDto commentDto, User auth);


    List<CommentResDto> findByMoment(Long id);

    //Comment like(Long id, Comment comment);
}
