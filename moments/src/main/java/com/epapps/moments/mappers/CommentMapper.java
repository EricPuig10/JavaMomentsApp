package com.epapps.moments.mappers;

import com.epapps.moments.dtos.comment.CommentRequestDto;
import com.epapps.moments.dtos.comment.CommentResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;

public class CommentMapper {
    public Comment mapReqToComment(CommentRequestDto req, Moment moment, User creator){
        Comment comment = new Comment();
        comment.setComment(req.getComment());
        comment.setMoment(moment);
        comment.setCreator(creator);
        return comment;
    }

    public CommentResDto mapCommentToRes(Comment comment) {
        CommentResDto resDto = new CommentResDto();
        resDto.setId(comment.getId());
        resDto.setMomentId(comment.getMoment().getId());
        resDto.setComment(comment.getComment());
        //resDto.setLiked(comment.isFaved(auth));
        resDto.setCreator( new UserMapper().mapUserToResDtoMoment(comment.getCreator()));
        return resDto;
    }

    /*
    public CommentResDto mapCommentToResWithAuth(Comment comment, User auth) {
        CommentResDto resDto = new CommentResDto();
        resDto.setId(comment.getId());
        resDto.setMomentId(comment.getMoment().getId());
        resDto.setComment(comment.getComment());
        resDto.setFaved(comment.isFaved(auth));
        resDto.setCreator( new UserMapper().mapUserToResDtoMoment(comment.getCreator()));
        return resDto;
    }*/
}
