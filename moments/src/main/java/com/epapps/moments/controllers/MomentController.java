package com.epapps.moments.controllers;

import com.epapps.moments.dtos.MomentRequestDto;
import com.epapps.moments.models.Moment;
import com.epapps.moments.models.User;
import com.epapps.moments.repositories.IMomentsRepository;
import com.epapps.moments.services.IMomentService;
import com.epapps.moments.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin (origins="*")
public class MomentController {

    private IMomentService momentService;
    private IUserService userService;

    public MomentController(IMomentService momentService, IUserService userService) {
        this.momentService = momentService;
        this.userService = userService;
    }

    private User getAuthUser() {
        return userService.getById(1L);
    }

    @GetMapping("/moments")
    List<Moment> getAll() {
        return this.momentService.getAll();
    }

    @GetMapping ("/moments/{id}")
    Moment getMomentById(@PathVariable Long id){
        var moment = this.momentService.findById(id);
        return moment;
    }

    @PostMapping("/moments")
    Moment createMoment(@RequestBody MomentRequestDto momentRequestDto){
        User authUser = getAuthUser();
        return momentService.create(momentRequestDto, authUser);
    }


    @PutMapping("/moments/{id}")
    Moment updateMoment(@PathVariable Long id, @RequestBody Moment momentToEdit){
        User authUser = getAuthUser();
        return momentService.updateAMoment(momentToEdit, authUser);
    }


    @DeleteMapping("/moments/{id}")
    public boolean deleteMoment(@PathVariable Long id) {
        User authUser = getAuthUser();
        return momentService.deleteMoment(id, authUser);
    }

    @PatchMapping("/moments/{id}/like")
    Moment like(@PathVariable Long id, @RequestBody Moment moment){
        //User auth = getAuthUser();
        return momentService.like(id, moment);
    }



    @GetMapping(value="/moments", params="search")
    List<Moment> getSearch(@RequestParam String search){
        return momentService.search(search);
    }

    @GetMapping("/moments/{id}/users")
    List<Moment> getMomentsByUser(@PathVariable Long id){
        return momentService.findByUserMoments(id);
    }


















}
