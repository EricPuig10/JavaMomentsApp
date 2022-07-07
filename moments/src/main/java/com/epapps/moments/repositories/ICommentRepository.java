package com.epapps.moments.repositories;

import com.epapps.moments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

}
