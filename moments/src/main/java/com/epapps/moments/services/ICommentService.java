package com.epapps.moments.services;

import com.epapps.moments.dtos.CommentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.User;

import java.util.List;
import java.util.Map;

public interface ICommentService {
    List<Comment> getAll();

    Comment create(CommentRequestDto commentDto, User userAuth);

}
