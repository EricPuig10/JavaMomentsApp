package com.epapps.moments.services;

import com.epapps.moments.dtos.CommentRequestDto;
import com.epapps.moments.dtos.MomentRequestDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.ICommentRepository;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.repositories.IUserRepository;
import org.springframework.stereotype.Service;

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
    public Comment likeComment( Long id ){
        Comment comment = this.commentRepository.findById(id).get();

        comment.setLiked(!comment.isLiked());
        final Comment likedComment = this.commentRepository.save(comment);
        return likedComment;
    }


}



