package com.epapps.moments.mappers;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.dtos.user.UserReqDto;
import com.epapps.moments.dtos.user.UserResDtoMoment;
import com.epapps.moments.dtos.user.UserResDto;
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

    public UserResDto mapUserToUserResDto(User user, User authUser){
        UserResDto res = new UserResDto();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setImg(user.getImg());
        res.setEmail(user.getEmail());
        res.setDateOfBirth(user.getDateOfBirth());
        res.setUbication(user.getUbication());
        res.setDescription(user.getDescription());
        return res;
    }

    public User mapRequestToUserToEdit(UserReqDto userDto, User user){
        user.setImg(userDto.getImg());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setUsername(userDto.getUsername());
        user.setDescription(userDto.getDescription());
        user.setUbication(userDto.getUbication());
        return user;
    }
}
