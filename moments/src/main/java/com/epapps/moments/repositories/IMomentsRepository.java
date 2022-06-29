package com.epapps.moments.repositories;

import com.epapps.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMomentsRepository extends JpaRepository<Moment, Long> {
    List<Moment> findByTitle(String search);

    @Query("select m from Moment m where m.title like %:search% or m.description like %:search%")
    List<Moment> findByTitleOrDescriptionContaining(@Param("search")String search);


















}

