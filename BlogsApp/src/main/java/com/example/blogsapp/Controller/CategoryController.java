package com.example.blogsapp.Controller;

import com.example.blogsapp.Model.Categories;
import com.example.blogsapp.Service.CategoriesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/Category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoriesService categoriesService;
    @GetMapping("/get")
    public ResponseEntity getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriesService.getAllCategories());
    }


    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid@RequestBody Categories categori, Errors errors){
    if (errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    Categories categories=categoriesService.addCategory(categori);
    return ResponseEntity.status(HttpStatus.OK).body("Successfully Update: "+categories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id,@Valid @RequestBody Categories categories,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Categories categories1=categoriesService.UpdateCategory(id, categories);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Update: "+categories1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletecategories(@PathVariable Integer id){
        Categories categories=categoriesService.deleteCategories(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Update: "+categories);
    }

}
