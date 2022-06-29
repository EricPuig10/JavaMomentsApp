package com.epapps.moments.controllers;

import com.epapps.moments.models.Moment;
import com.epapps.moments.repositories.IMomentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin (origins="http://localhost:4000")
public class MomentController {

    private IMomentsRepository momentsRepository;

    @Autowired
    public MomentController(IMomentsRepository momentsRepository) {
        this.momentsRepository = momentsRepository;
    }

    @GetMapping("/moments")
    List<Moment> getAll() {
        var momentsList = this.momentsRepository.findAll();
        return momentsList;
    }
    @GetMapping ("/moments/{id}")
    Moment getMomentById(@PathVariable Long id){
        var moment = this.momentsRepository.findById(id).get();
        return moment;
    }

    @PostMapping("/moments")
    Moment createMoment(@RequestBody Moment newMoment){
        var moment = momentsRepository.save(newMoment);
        return moment;
    }

    @PutMapping("/moments/{id}")
    Moment updateMoment(@PathVariable Long id, @RequestBody Moment momentToEdit){

        Moment moment = this.momentsRepository.findById(id).get();

        moment.setTitle(momentToEdit.getTitle());;
        moment.setDescription(momentToEdit.getDescription());
        moment.setImgUrl(momentToEdit.getImgUrl());
        moment.setUbication(momentToEdit.getUbication());
        moment.setLiked(!moment.isLiked());
        final Moment updatedMoment = this.momentsRepository.save(moment);
        return updatedMoment;
    }

    @DeleteMapping("/moments/{id}")
    public boolean deleteMoment(@PathVariable Long id) {
        Moment moment = this.momentsRepository.findById(id).get();
        this.momentsRepository.delete(moment);
        return true;
    }

    @GetMapping(value="/moments", params="search")
    List<Moment> getSearch(@RequestParam String search){
        var searchCollection = this.momentsRepository.findByTitleOrDescriptionContaining(search);
        return searchCollection;
    }

    @PatchMapping("/moments/{id}/{action}")
    Moment saveMoment(@PathVariable Long id, @PathVariable String action){
        Moment moment = this.momentsRepository.findById(id).get();
        if(action.equals("like")){ moment.setLiked(!moment.isLiked());}
        final Moment updatedMoment = this.momentsRepository.save(moment);
        return updatedMoment;
    }
















}
