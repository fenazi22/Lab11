package com.example.blogsapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Posts {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @NotNull(message = "Must be Input title")
   @Column(columnDefinition = "varchar(20) not null unique")
   private String title;


   @NotEmpty(message ="Must be Content Not Empty")
  @Size(min = 10,message = " \"Must be input Content more than 10\"")
   @Column(columnDefinition = "varchar(225) not null")
   private String content;

   @NotNull(message = "Must be N0T ID NUll")
   @Column(columnDefinition = "int not null ")
   private Integer author_id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   @DateTimeFormat(pattern = "YYY-MM-DD")
   @Column(columnDefinition = "Date not null")
   private LocalDate publication_date=LocalDate.now();



}
