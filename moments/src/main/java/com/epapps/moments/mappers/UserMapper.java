package com.epapps.moments.mappers;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.dtos.user.UserResDtoMoment;
import com.epapps.moments.dtos.user.UserWithoutPasswordResDto;
import com.epapps.moments.models.User;

public class UserMapper {
    public UserResDtoMoment mapUserToResDtoMoment(User user) {
        UserResDtoMoment res = new UserResDtoMoment();
        res.setName(user.getName());
        res.setUserName(user.getUserName());
        res.setUserImg(user.getUserImg());
        res.setId(user.getId());
        return res;

    }

    public User mapCreateReqToUser(UserCreateDto req){
        User user = new User();
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setUserName(req.getUserName());
        user.setPassword(req.getPassword());
        return user;
    }

    public UserWithoutPasswordResDto mapUserToUserWithoutPasswordDto(User user){
        UserWithoutPasswordResDto res = new UserWithoutPasswordResDto();
        res.setId(user.getId());
        res.setUserName(user.getUserName());
        res.setUserImg(user.getUserImg());
        res.setEmail(user.getEmail());
        res.setName(user.getName());
        res.setDateOfBirth(user.getDateOfBirth());
        res.setUbication(user.getUbication());
        res.setFollowers(user.getFollowers());
        res.setFollowing(user.getFollowing());
        res.setDescription(user.getDescription());
        return res;
    }
}
