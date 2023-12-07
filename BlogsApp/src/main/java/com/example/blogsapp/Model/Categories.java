package com.example.blogsapp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Categories {

    @Id
   private Integer id;


    @NotEmpty(message = "Must be NoT Empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String category_name;

}
