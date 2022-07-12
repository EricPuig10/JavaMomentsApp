package com.epapps.moments.repositories;

import com.epapps.moments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
@Query("select c from Comment c where c.moment.id = :id")
     List<Comment> findByMomentId(@Param("id")Long id);

     @Query("select c from Comment c where c.moment.id = :id order by c.id DESC")
     List<Comment> getCommentsByMomentIdReverse(@Param("id") Long id);


}
