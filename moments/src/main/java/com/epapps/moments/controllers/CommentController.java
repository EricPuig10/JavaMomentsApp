
package com.epapps.moments.controllers;

import com.epapps.moments.auth.facade.IAuthenticationFacade;
import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.dtos.comment.CommentResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models2.User;
import com.epapps.moments.services.ICommentService;
import com.epapps.moments.services.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CommentController {
    private ICommentService commentService;
    IAuthenticationFacade authenticationFacade;

    public CommentController (ICommentService commentService , IAuthenticationFacade authenticationFacade){
        this.commentService = commentService;
        this.authenticationFacade = authenticationFacade;
    }

    private User getAuth(){
        return authenticationFacade.getAuthUser();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/comments")
    List<CommentResDto> getAll(){
        return this.commentService.getAll();
    }


    @CrossOrigin(origins = "*")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/comments")
    CommentResDto createComment(@RequestBody CommentRequestDto commentDto){
        User auth = this.getAuth();
        return commentService.create(commentDto, auth);
    }

    @GetMapping ("/comments/{id}")
    Comment getCommentById(@PathVariable Long id){
        var comment = this.commentService.findById(id);
        return comment;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/moments/{id}/comments")
    List<CommentResDto> getMomentComments(@PathVariable Long id){
        return commentService.findByMoment(id);
    }


    /*
    @PatchMapping("/comments/{id}/like")
    Comment like(@PathVariable Long id, @RequestBody Comment comment){
        //User auth = getAuthUser();
        return commentService.like(id, comment);
    }*/







}

