package com.epapps.moments.services;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.dtos.user.UserWithoutPasswordResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;

import java.util.List;

public interface IUserService {

    User getById(Long id);

    List<User> findAll();

    UserWithoutPasswordResDto createUser(UserCreateDto userCreateDto);


}
