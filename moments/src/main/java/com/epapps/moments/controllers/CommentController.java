
package com.epapps.moments.controllers;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.User;
import com.epapps.moments.services.ICommentService;
import com.epapps.moments.services.IUserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping ("/comments/{id}")
    Comment getCommentById(@PathVariable Long id){
        var comment = this.commentService.findById(id);
        return comment;
    }

    @PutMapping("/comments/{id}")
    Comment updateComment(@PathVariable Long id, @RequestBody Comment comment){
        User authUser = getAuthUser();
    return this.commentService.updateComment(comment, authUser);
    }

    @GetMapping("/moments/{id}/comments")
    List<Comment> getMomentComments(@PathVariable Long id){
        return commentService.findByMoment(id);
    }

    @PatchMapping("/comments/{id}/like")
    Comment like(@PathVariable Long id, @RequestBody Comment comment){
        //User auth = getAuthUser();
        return commentService.like(id, comment);
    }




    private User getAuthUser() {
        return userService.getById(1L);
    }


}

