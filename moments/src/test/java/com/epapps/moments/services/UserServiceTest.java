package com.epapps.moments.services;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.models2.User;
import com.epapps.moments.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserServiceTest {


    @Mock
    IUserRepository userRepository;

    public User create(Long id){
        User user = new User();
        user.setId(id);
        user.setUsername("username");
        user.setImg("avatar");
        user.setEmail("email");
        user.setPassword("password");
        user.setDateOfBirth("date");
        user.setDescription("description");
        user.setUbication("ubication");
        user.setFollowers(2);
        user.setFollowing(2);
        return user;
    }

    @Test
    void getByIdReturnsAUser() {
        var userService = new UserService(userRepository);

        var user = new User();
        user.setId(1L);
        user.setUsername("user");

        Long id = 1L;

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        var sut = userService.getById(id);

        assertThat (sut.getUsername(), equalTo(user.getUsername()));
    }

    @Test
    void findAllReturnsAListOfUsers() {
        var userService = new UserService(userRepository);

        var usersList = List.of(new User(), new User());

        Mockito.when(userRepository.findAll()).thenReturn(usersList);

        var sut = userService.findAll();

        assertThat (sut.size(), equalTo(2));
    }



}