package com.epapps.moments.repositories;

import com.epapps.moments.models.Moment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FakeMomentsRepository/* implements IMomentsRepository */{
    /*
    @Override
    public List<Moment> findAll() {
        var momentsList = this.getMomentsList();

        return momentsList;
    }

    @Override
    public Moment findById(Long id){
        var momentsList = this.getMomentsList();
        var moment = momentsList.stream()
                .filter(item -> item.getId() == id ).findFirst().get();
        return moment;
    }

    @Override
    public List<Moment>  searchAllByTitle(String title) {
        var momentsList = this.getMomentsList();
        var moment = momentsList.stream().filter((p)->p.getTitle().contains(title)).collect(Collectors.toList());
        return moment;
    }

    @Override
    public List<Moment> searchAllByDescription(String description){
        var momentsList = this.getMomentsList();
        var moment = momentsList.stream().filter((p)->p.getDescription().contains(description)).collect(Collectors.toList());
        return moment;
    }
    private List<Moment> getMomentsList() {
        return List.of(
                new Moment("Moment1", 1L, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg", "This is the moment 1"),
                new Moment("Moment2", 2L, "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80", "This is the moment 2"),
                new Moment("Moment3", 3L, "https://www.gettyimages.es/gi-resources/images/500px/983794168.jpg", "This is the moment 3 cat")
        );
    }*/

}
