package com.epapps.moments.services;

import com.epapps.moments.dtos.moment.MomentResDto;
import com.epapps.moments.dtos.user.UserReqDto;
import com.epapps.moments.dtos.user.UserResDto;
import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.mappers.MomentMapper;
import com.epapps.moments.mappers.UserMapper;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.IUserRepository;
import com.factoria.moments.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

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
    public UserResDto updateUser(UserReqDto userDto, Long id, User authUser) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) throw new NotFoundException("User Not Found", "M-404");

        if (!user.get().getId().equals(authUser.getId()))
            throw new BadRequestException("User can't be edited if you arent user", "P-153");
        User updatedUser = new UserMapper().mapRequestToUserToEdit(userDto, user.get());
        userRepository.save(updatedUser);
        UserResDto userRes = new UserMapper().mapUserToUserResDto(updatedUser, authUser);
        return userRes;
    }
    //aqui anira update

}
