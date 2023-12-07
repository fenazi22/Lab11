package com.example.blogsapp.Controller;


import com.example.blogsapp.Model.Posts;
import com.example.blogsapp.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/posts")
public class PostController {
    private final PostService postService;
    @GetMapping("/get")
    public ResponseEntity getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addNewPosts(@Valid@RequestBody Posts post, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

//        return ResponseEntity.status(HttpStatus.OK).body("Successfully add Author ID:"+ posts.getAuthor_id()+" | Title: "+posts.getTitle()+" | getPublication_date: " +posts.getPublication_date());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully add "+ postService.addNewPost(post));
    }


@PutMapping("/update/{id}")
    public ResponseEntity updatePosts(@PathVariable Integer id,@Valid @RequestBody Posts posts,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

    Posts posts1= postService.updatePosts(id, posts);
        return  ResponseEntity.status(HttpStatus.OK).body("Updated Title: "+posts1.getTitle()+"| Content: "+posts1.getContent()+" | Updated dateTime: "+posts1.getPublication_date().atTime(LocalTime.now()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePosts(@PathVariable Integer id){

        Posts posts=postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted Posts: Title"+posts.getTitle()+" | Delete dateTime: | "+posts.getPublication_date().atTime(LocalTime.now()));
    }



    @GetMapping("/title/{titles}")
    public ResponseEntity SearchByTitle(@PathVariable String titles){
        Posts posts=postService.SearchByTitle(titles);
    return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity SearchByAuthorId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.SearchByAuthorId(id));
    }

    @GetMapping("/dates/{date}")
    public ResponseEntity searchByDateBefore(@PathVariable Date date){
        return ResponseEntity.status(HttpStatus.OK).body(postService.searchByDateBefore(date));


    }


}
