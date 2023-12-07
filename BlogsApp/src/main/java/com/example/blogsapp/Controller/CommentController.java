package com.example.blogsapp.Controller;

import com.example.blogsapp.Model.Comments;
import com.example.blogsapp.Service.CommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/comment")
public class CommentController {

    private final CommentsService commentsService;
    @GetMapping("/get")
    public ResponseEntity getAllComments(){
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComments(@Valid @RequestBody Comments comment, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Comments comments=commentsService.addComments(comment);
        return ResponseEntity.status(HttpStatus.OK).body("Seccessfully Add Comment Author ID: "+comments.getAuthor_id()+"| Added Date: "+comments.getComment_date().atTime(LocalTime.now()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@Valid @RequestBody Comments comment, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

    Comments comment1=commentsService.UpdateComments(id, comment);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully update : getContent: "+comment1.getContent()+" | UpdateComment dateTime: "+comment1.getComment_date().atTime(LocalTime.now()));
    }


    @DeleteMapping("/delete/{id}")
public ResponseEntity deleteComment(@PathVariable Integer id){

        Comments comments=commentsService.deleteComments(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete ID:"+comments.getPost_id()+"| Deleted dateTime: "+comments.getComment_date());
    }


}
