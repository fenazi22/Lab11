package com.example.blogsapp.Service;

import com.example.blogsapp.Api.ApiException;
import com.example.blogsapp.Model.Comments;
import com.example.blogsapp.Model.Posts;
import com.example.blogsapp.Repository.CommentRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentRepo commentRepo;
    private final PostService postService;
    public List<Comments> getAllComments() {
        return commentRepo.findAll();

    }

    public Comments addComments(Comments comment){
        List<Posts> posts=postService.getAllPosts();
        for (int i = 0; i <posts.size() ; i++) {
          if (comment.getPost_id()==null || comment.getAuthor_id()==null) {
             throw new ApiException("Sorry Not found!!");
          }
            comment.setPost_id(posts.get(i).getId());
            comment.setAuthor_id(posts.get(i).getAuthor_id());
        }
        commentRepo.save(comment);
        return comment;
    }


    public  Comments UpdateComments(Integer id ,Comments comment){
        Comments oldcomments=commentRepo.findAllById(id);
        if (oldcomments==null){
            throw new ApiException("Sorry Not found!!");
        }

    oldcomments.setComment_date(comment.getComment_date());
    oldcomments.setContent(comment.getContent());
    oldcomments.setAuthor_id(comment.getAuthor_id());

    commentRepo.save(oldcomments);
    return oldcomments;
    }

    public Comments deleteComments(Integer id){
        Comments comments=commentRepo.findAllById(id);
        if (comments==null){
            throw  new ApiException("Not found!!");
        }
        commentRepo.delete(comments);
        return comments;
    }

}
