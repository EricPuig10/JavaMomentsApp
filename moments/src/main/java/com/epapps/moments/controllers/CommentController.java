
package com.epapps.moments.controllers;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.dtos.comment.CommentResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.User;
import com.epapps.moments.services.ICommentService;
import com.epapps.moments.services.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CommentController {
    private ICommentService commentService;


    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    List<CommentResDto> getAll(){
        return this.commentService.getAll();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/comments")
    CommentResDto createComment(@RequestBody CommentRequestDto commentDto){
        return commentService.create(commentDto);
    }

    @GetMapping ("/comments/{id}")
    Comment getCommentById(@PathVariable Long id){
        var comment = this.commentService.findById(id);
        return comment;
    }

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

