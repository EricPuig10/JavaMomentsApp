package com.epapps.moments.services;

import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;

import java.util.List;

public interface IUserService {

    User getById(Long id);

    List<User> findAll();

    User findById(Long id);

    User createUser(User user);

    List<Moment> findByUserMoments(Long id);
}
