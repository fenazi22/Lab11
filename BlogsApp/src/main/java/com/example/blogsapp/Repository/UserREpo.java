package com.example.blogsapp.Repository;

import com.example.blogsapp.Model.Users;
import com.example.blogsapp.Service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserREpo extends JpaRepository<Users,Integer> {
    Users findAllById(Integer id);
    Users findByUsername(String username);

    List<Users> findByIdBetween(Integer firstId, Integer secondId );

}
