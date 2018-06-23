package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

}
