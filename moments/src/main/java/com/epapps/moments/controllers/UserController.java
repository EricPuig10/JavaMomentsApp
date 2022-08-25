package com.epapps.moments.controllers;

import com.epapps.moments.auth.facade.IAuthenticationFacade;
import com.epapps.moments.dtos.user.UserReqDto;
import com.epapps.moments.dtos.user.UserResDto;
import com.epapps.moments.models2.User;
import com.epapps.moments.services.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class UserController {

    IUserService userService;
    IAuthenticationFacade authenticationFacade;
    public UserController(IUserService userService, IAuthenticationFacade authenticationFacade){
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    public User getAuth(){
        return this.authenticationFacade.getAuthUser();
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/users")
    List<User> getAll(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/users/{id}")
    UserResDto updateUser(@PathVariable Long id, @RequestBody UserReqDto userDto) {
        User authUser = authenticationFacade.getAuthUser();
        return userService.updateUser(userDto, id, authUser);}




    }
