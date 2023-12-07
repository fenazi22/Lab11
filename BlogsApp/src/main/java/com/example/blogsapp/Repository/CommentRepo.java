package com.example.blogsapp.Repository;

import com.example.blogsapp.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comments,Integer> {

Comments findAllById(Integer id);
}
