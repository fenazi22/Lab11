package com.example.blogsapp.Repository;

import com.example.blogsapp.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesREpo extends JpaRepository<Categories,Integer> {
Categories findAllById(Integer id);


}
