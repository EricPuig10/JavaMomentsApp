package com.epapps.moments.services;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.dtos.comment.CommentResDto;
import com.epapps.moments.mappers.CommentMapper;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
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
    public List<CommentResDto> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List <CommentResDto> commentsList = new ArrayList<>();
        comments.forEach(Comment -> {
            commentsList.add(new CommentMapper().mapCommentToRes(Comment));
        });
        return commentsList;
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }


    @Override
    public CommentResDto create(CommentRequestDto commentDto, User auth) {
        var moment = this.momentsRepository.findById(commentDto.getMomentId()).get();
        var creator = auth;
        Comment comment = new CommentMapper().mapReqToComment(commentDto, moment, creator);
        this.commentRepository.save(comment);
        return new CommentMapper().mapCommentToRes(comment);

    }


    @Override
    public List<CommentResDto> findByMoment(Long id) {
        List<CommentResDto> momentComments = new ArrayList<>();
        commentRepository.findByMomentId(id).forEach(Comment ->{
            momentComments.add( new CommentMapper().mapCommentToRes(Comment));
        });
        return momentComments;
    }

    /*
    @Override
    public Comment like(Long id, Comment comment) {
        Comment commentToLike = commentRepository.findById(comment.getId()).get();
        //if(moment.getCreator().getId() == auth.getId()) return null;
        comment.setLiked(!commentToLike.isLiked());
        Comment commentLiked = commentRepository.save(comment);
        return commentLiked;
    }*/



}




