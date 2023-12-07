package com.example.blogsapp.Service;

import com.example.blogsapp.Api.ApiException;
import com.example.blogsapp.Model.Categories;
import com.example.blogsapp.Repository.CategoriesREpo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoriesService {
    private  final CategoriesREpo categoriesREpo;

   public List<Categories>getAllCategories(){
       return categoriesREpo.findAll();
   }

   public Categories addCategory(Categories categories){
       Categories categories1=categoriesREpo.save(categories);
       return categories1;
   }

public Categories UpdateCategory(Integer id,Categories categories){
       Categories categories1=categoriesREpo.findAllById(id);
       if (categories1==null){
           throw  new ApiException("Not Found!!");
       }
       categories1.setCategory_name(categories.getCategory_name());
       categoriesREpo.save(categories);
       return categories1;
}

public  Categories deleteCategories(Integer id){
       Categories categories=categoriesREpo.findAllById(id);
       if (categories==null){
           throw  new ApiException("Sorry Not Found!!");
       }
       categoriesREpo.delete(categories);
    return categories;
   }

}
