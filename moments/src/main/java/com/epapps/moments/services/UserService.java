package com.epapps.moments.services;

import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User createUser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUserName(user.getUserName());
        newUser.setUserImg(user.getUserImg());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return userRepository.save(user);
    }


}
