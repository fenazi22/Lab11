package com.example.blogsapp.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Must be input username! ")
    @Column(columnDefinition = "varchar(20) not null")
    private String username;


    @Email(message = "Please Enter Email")
    @NotNull(message = "Must be input email! ")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;


    @NotNull(message = "Must be input password! ")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;


    @DateTimeFormat(pattern = "YYY-MM-DD  HH:mm:ss")
    @Column(columnDefinition = "DateTime not null")
    // Endpoint.
    private LocalDate registration_date=LocalDate.now();




}
