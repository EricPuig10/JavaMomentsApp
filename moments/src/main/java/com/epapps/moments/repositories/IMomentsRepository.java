package com.epapps.moments.repositories;

import com.epapps.moments.models.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface IMomentsRepository extends JpaRepository<Moment, Long> {
    List<Moment> findByTitle(String search);

    @Query("select m from Moment m where m.description like %:search% or m.title like %:search%")
    List <Moment> findByDescriptionOrTitleContaining(@Param("search") String search);



    @Query("select m from Moment m where m.creator.id = :id")
    List<Moment> getMomentsByUserId(@Param("id") Long id);

    @Query("select m from Moment m inner join m.favs favs where favs.faver.id = :id")
    List<Moment> findFavs(@Param("id") Long id);
}

