package com.example.blogsapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Must be Input post_id")
    @Column(columnDefinition = "int not null")
    private Integer post_id;

    @NotNull(message = "Must be Input author_id")
    @Column(columnDefinition = "int not null")
    private Integer author_id;

    @NotEmpty(message = "Not Empty content")
    @Column(columnDefinition = "varchar(100) not null")
    private String content;


    @DateTimeFormat(pattern = "YYY-MM-DD")
    @Column(columnDefinition = "Date not null")
    private LocalDate comment_date=LocalDate.now();


}
