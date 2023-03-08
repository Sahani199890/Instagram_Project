package com.example.instagram.controller;


import com.example.instagram.dao.UserRepo;
import com.example.instagram.model.Post;
import com.example.instagram.model.User;
import com.example.instagram.service.PostService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    private UserRepo userRepo;


    @PostMapping(value = "/post")
    public ResponseEntity<String> savePost(@RequestBody String postRequest) {

        Post post = setPost(postRequest);
        int postId = postService.savePost(post);
        return new ResponseEntity<>(String.valueOf(postId), HttpStatus.CREATED);
    }



    @GetMapping(value = "/post")
    public ResponseEntity<String> getPost(@RequestParam String userId, @Nullable @RequestParam String postId) {

        JSONArray postArr = postService.getPost(Integer.valueOf(userId), postId);
        return new ResponseEntity<>(postArr.toString(), HttpStatus.OK);
    }




    @PutMapping(value = "/post/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody String postRequest) {

        Post post = setPost(postRequest);
        postService.updatePost(postId, post);
        return  new ResponseEntity<>("Post updated", HttpStatus.OK);


    }

    private Post setPost(String postData) {
        JSONObject json=new JSONObject(postData);
        int userId=json.getInt("userId");
        if(userRepo.findById(userId).isPresent()){
            User user=userRepo.findById(userId).get();
            Post post=new Post();
            post.setUser(user);
            post.setPostData(json.getString("postData"));
            Timestamp createdTime=new Timestamp(System.currentTimeMillis());
            post.setCreatedDate(createdTime);
            return post;
        }else{
            return null;
        }
    }
}
