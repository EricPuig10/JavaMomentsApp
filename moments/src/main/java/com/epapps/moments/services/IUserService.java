package com.epapps.moments.services;

import com.epapps.moments.dtos.user.UserReqDto;
import com.epapps.moments.dtos.user.UserResDto;
import com.epapps.moments.models2.User;

import java.util.List;

public interface IUserService {

    User getById(Long id);

    List<User> findAll();

    UserResDto updateUser(UserReqDto userDto, Long id, User authUser);

    //update


}
