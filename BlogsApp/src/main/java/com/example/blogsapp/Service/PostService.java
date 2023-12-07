package com.example.blogsapp.Service;

import com.example.blogsapp.Api.ApiException;
import com.example.blogsapp.Model.Posts;
import com.example.blogsapp.Model.Users;
import com.example.blogsapp.Repository.PostsREpo;
import com.example.blogsapp.Repository.UserREpo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostsREpo postsREpo;
    private final UserService userService;
    public List<Posts> getAllPosts(){
    List<Posts> posts=postsREpo.findAll();
    return posts;
    }

    public Posts addNewPost(Posts post){
        List<Users> users=userService.getAllUsers();
        for (int i = 0; i <users.size() ; i++) {
            if (post.getAuthor_id()!=users.get(i).getId()){
              throw  new ApiException("wrong author ID not ");
            }

            post.setAuthor_id(users.get(i).getId());
            postsREpo.save(post);
        }
    return post;
    }

    public Posts updatePosts(Integer id,Posts posts){
        Posts oldPost=postsREpo.findAllById(id);
        if (oldPost==null){
            throw  new ApiException("Sorry Not Found");
        }

        oldPost.setTitle(posts.getTitle());
        oldPost.setContent(posts.getContent());
        oldPost.setPublication_date(posts.getPublication_date());
        postsREpo.save(oldPost);
        return oldPost;
    }




public Posts deletePost(Integer id){
        Posts post=postsREpo.findAllById(id);
        if (post==null){
            throw  new ApiException("Sorry not Found");
        }
        postsREpo.delete(post);
    return post;
    }

    //endpoint--1
    public  Posts SearchByTitle(String title){
        Posts posts=postsREpo.findAllByTitle(title);
        if (posts==null){
            throw new RuntimeException("Sorry Not found!!");
        }
        return posts;
    }

    //endpoint--3
    public Posts SearchByAuthorId(Integer id){
        Posts posts=postsREpo.findAllByAuthor_id(id);
        if (posts==null){
            throw new RuntimeException("Sorry Not found!!");
        }
        return posts;
    }

    public  Posts searchByDateBefore(Date date){
        Posts posts=postsREpo.findAllByPublication_dateBefore(date);
        if (posts==null){
            throw new ApiException("Not found!!");
        }
        return posts;
    }

}
