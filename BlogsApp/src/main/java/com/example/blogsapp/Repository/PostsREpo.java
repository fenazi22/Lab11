package com.example.blogsapp.Repository;

import com.example.blogsapp.Model.Posts;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PostsREpo  extends JpaRepository<Posts,Integer> {

    Posts findAllById(Integer id);
    Posts findAllByTitle(String title);
    Posts findAllByAuthor_id(Integer id);

    Posts findAllByPublication_dateBefore(Date date);

}
