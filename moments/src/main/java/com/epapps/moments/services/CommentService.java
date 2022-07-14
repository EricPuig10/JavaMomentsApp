package com.epapps.moments.services;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements ICommentService{

    private ICommentRepository commentRepository;
    private IMomentsRepository momentsRepository;
    private IUserRepository userRepository;

    public CommentService(ICommentRepository commentRepository, IMomentsRepository momentsRepository, IUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.momentsRepository = momentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }


    @Override
    public Comment create(CommentRequestDto commentDto, User auth) {
        Comment comment = new Comment();
        Moment moment = this.momentsRepository.findById(commentDto.getMomentId()).get();
        comment.setComment(commentDto.getComment());
        comment.setMoment(moment);
        comment.setCreator(auth);
        return commentRepository.save(comment);

    }

    @Override
    public Comment updateComment(Comment comment, User auth ){
        Comment commentToUpdate = this.commentRepository.findById(comment.getId()).get();
        commentToUpdate.setComment(comment.getComment());
        commentToUpdate.setCreator(auth);
        final Comment updatedComment = this.commentRepository.save(commentToUpdate);
        return updatedComment;
    }

    @Override
    public List<Comment> findByMoment(Long id) {


        List<Comment> momentComments = new ArrayList<>();

        commentRepository.getCommentsByMomentIdReverse(id).forEach(momentComments::add);

        return momentComments;
    }

    @Override
    public Comment like(Long id, Comment comment) {
        Comment commentToLike = commentRepository.findById(comment.getId()).get();
        //if(moment.getCreator().getId() == auth.getId()) return null;
        comment.setLiked(!commentToLike.isLiked());
        Comment commentLiked = commentRepository.save(comment);
        return commentLiked;
    }



}




