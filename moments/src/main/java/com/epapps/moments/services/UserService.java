package com.epapps.moments.services;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.dtos.user.UserWithoutPasswordResDto;
import com.epapps.moments.mappers.UserMapper;
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
    public UserWithoutPasswordResDto createUser(UserCreateDto userCreateDto) {

        User user = new UserMapper().mapCreateReqToUser(userCreateDto);
        return new UserMapper().mapUserToUserWithoutPasswordDto(userRepository.save(user));

    }


}
