package com.epapps.moments.controllers;

import com.epapps.moments.dtos.CommentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4000")
public class CommentController {
    private ICommentRepository commentRepository;
    private IMomentsRepository momentsRepository;

    public CommentController(ICommentRepository commentRepository, IMomentsRepository momentsRepository) {
        this.commentRepository = commentRepository;
        this.momentsRepository = momentsRepository;
    }

    @GetMapping("/comments")
    List<Comment> getAll(){
        return this.commentRepository.findAll();
    }

    @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentDto){
        var newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        var moment = this.momentsRepository.findById(commentDto.getMomentId()).get();
        newComment.setMoment(moment);
        return this.commentRepository.save(newComment);
    }

    /*
    @GetMapping("/momen/{id}")
    Comment getCommentsByMomentId(@PathVariable Long id) {
        var comment = this.commentRepository.findById(id).get();
        return comment;
    }*/
}
