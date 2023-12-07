package com.example.blogsapp.Controller;

import com.example.blogsapp.Model.Users;
import com.example.blogsapp.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addNewUser(@Valid @RequestBody Users user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        Users users=userService.addNewUser(user);
    return ResponseEntity.status(HttpStatus.OK).body("Successfully Adda User ID: "+users.getId()+" | Username: "+users.getUsername()+" | getRegistration_date: "+users.getRegistration_date());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid @RequestBody Users user,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
Users user1  =    userService.UpdateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Update ID :"+user1.getId()+"| UserName:  "+user1.getUsername()+" |Update date and Time: "+user1.getRegistration_date().atTime(LocalTime.now(Clock.systemDefaultZone())) );
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity DeleteUser(@PathVariable Integer id){
    Users user=userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successfully Delete ID:"+user.getId()+" | UserName: "+user.getUsername());
    }

@GetMapping("/check/{username}/{pass}")
public ResponseEntity checkUsername(@PathVariable String username,@PathVariable String pass){
    Users users=userService.checkpass(username,pass);
return ResponseEntity.status(HttpStatus.OK).body("Correct UserName& & password");
}

@GetMapping("/checkUsers/{first}/{last}")
    public ResponseEntity  checkUsers(@PathVariable Integer first,@PathVariable Integer last){
    return ResponseEntity.status(HttpStatus.OK).body(userService.searchID(first, last));

}




    }

