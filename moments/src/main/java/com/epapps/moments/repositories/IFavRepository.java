package com.epapps.moments.repositories;

import com.epapps.moments.models.Comment;
import com.epapps.moments.models.Fav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFavRepository extends JpaRepository<Fav, Long> {

    @Query("select f from Fav f where f.moment.id = :id")
    List<Fav> findByMomentId(@Param("id") Long id);

}
