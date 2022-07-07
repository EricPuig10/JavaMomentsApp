
package com.epapps.moments.controllers;

import com.epapps.moments.dtos.CommentRequestDto;
import com.epapps.moments.dtos.MomentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.services.ICommentService;
import com.epapps.moments.services.IUserService;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4000/")
public class CommentController {
    private ICommentService commentService;
    private IUserService userService;


    public CommentController(ICommentService commentService, IUserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/comments")
    List<Comment> getAll(){
        return this.commentService.getAll();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentDto){
        User authUser = getAuthUser();
        return commentService.create(commentDto, authUser);
    }



    private User getAuthUser() {
        return userService.getById(1L);
    }


}

