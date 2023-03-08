package com.example.instagram.controller;

import com.example.instagram.model.User;
import com.example.instagram.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> saveUser(@RequestBody String userData){
        User user=setUser(userData);
        int userId=userService.addUser(user);
        return new ResponseEntity<>("user saved Id --> "+userId, HttpStatus.CREATED);
    }

    private User setUser(String userData) {
        JSONObject json=new JSONObject(userData);
        User user=new User();
        user.setAge(json.getInt("age"));
        user.setEmail(json.getString("email"));
        user.setNumber(json.getString("number"));
        user.setFName(json.getString("fName"));
        user.setLName(json.getString("lName"));
        return user;
    }

    @GetMapping("/get-user")
    public ResponseEntity<String> getUser(@Nullable @RequestParam String userId){
        JSONArray userDetails=userService.getUser(userId);
        return new ResponseEntity<>(userDetails.toString(),HttpStatus.FOUND);
    }
    @PutMapping("/update-user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId,@RequestBody String userData){
        User user=setUser(userData);
        userService.updateUser(user,userId);
        return new ResponseEntity<>("user updated",HttpStatus.OK);
    }


}
