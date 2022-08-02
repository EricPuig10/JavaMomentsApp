package com.epapps.moments.controllers;

import com.epapps.moments.dtos.user.UserCreateDto;
import com.epapps.moments.dtos.user.UserWithoutPasswordResDto;
import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models2.User;
import com.epapps.moments.services.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class UserController {

    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping("/users")
    UserWithoutPasswordResDto createUser(@RequestBody UserCreateDto userCreateDto){
        return userService.createUser(userCreateDto);
    }




}
