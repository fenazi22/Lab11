package com.example.blogsapp.Service;

import com.example.blogsapp.Api.ApiException;
import com.example.blogsapp.Model.Users;
import com.example.blogsapp.Repository.UserREpo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserREpo userREpo;

    public List<Users> getAllUsers(){
        return userREpo.findAll();
    }

    public Users addNewUser(Users user){
        userREpo.save(user);
        return user;
    }


    public Users UpdateUser(Integer id,Users users){

        Users oldUser =  userREpo.findAllById(id);

        if (oldUser==null){
            throw  new ApiException("Sorry Not Found!!");
        }

        oldUser.setUsername(users.getUsername());
        oldUser.setPassword(users.getPassword());
        oldUser.setEmail(users.getEmail());
        oldUser.setRegistration_date(users.getRegistration_date());
        userREpo.save(oldUser);
        return oldUser;
    }


    public Users deleteUser(Integer id){
        Users user=userREpo.findAllById(id);
        if (user==null){
            throw  new ApiException("Sorry Not found ID");
        }
        userREpo.delete(user);
        return user;
    }

    public Users checkpass(String username,String pass){
        Users users=userREpo.findByUsername(username);
        if (username==null){
            throw new ApiException("not Found Username");
        }
        if (users.getUsername().equals(username) && !users.getPassword().equals(pass)) {
            throw new ApiException("userName or Pass is  Not Correct");
        }

        return users;

    }

    //endpoint--4
    public List<Users> searchID(Integer first,Integer last){
      List <Users> users=userREpo.findByIdBetween(first, last);
        if (users==null){
            throw  new ApiException("Not found!!");

        }


      return users;
    }
}
