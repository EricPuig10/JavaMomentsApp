package com.epapps.moments.mappers;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.dtos.user.UserResDtoMoment;
import com.epapps.moments.dtos.user.UserWithoutPasswordResDto;
import com.epapps.moments.models2.User;

public class UserMapper {
    public UserResDtoMoment mapUserToResDtoMoment(User user) {
        UserResDtoMoment res = new UserResDtoMoment();
        res.setUsername(user.getUsername());
        res.setImg(user.getImg());
        res.setId(user.getId());
        return res;

    }

    public User mapCreateReqToUser(UserCreateDto req){
        User user = new User();
        //user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        return user;
    }

    public UserWithoutPasswordResDto mapUserToUserWithoutPasswordDto(User user){
        UserWithoutPasswordResDto res = new UserWithoutPasswordResDto();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setImg(user.getImg());
        res.setEmail(user.getEmail());
        res.setDateOfBirth(user.getDateOfBirth());
        res.setUbication(user.getUbication());
        res.setFollowers(user.getFollowers());
        res.setFollowing(user.getFollowing());
        res.setDescription(user.getDescription());
        return res;
    }
}
