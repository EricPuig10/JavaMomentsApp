package com.epapps.moments.repositories;

import com.epapps.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface IMomentsRepository extends JpaRepository<Moment, Long> {
    List<Moment> findByTitle(String search);

    @Query("select m from Moment m " +
            "where upper(m.title) like upper(concat('%', ?1, '%')) or upper(m.description) like upper(concat('%', ?2, '%'))")
    List<Moment> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);


    @Query("select m from Moment m where m.creator.id = :id")
    List<Moment> getMomentsByUserId(@Param("id") Long id);

}

