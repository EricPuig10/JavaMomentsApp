package com.epapps.moments.repositories;

import com.epapps.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMomentsRepository extends JpaRepository<Moment, Long> {
    List<Moment> findByTitle(String search);

}

