package com.example.instagram.service;

import com.example.instagram.dao.UserRepo;
import com.example.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public int addUser(User user) {
        User save = userRepo.save(user);
        return save.getUserId();
    }

    public JSONArray getUser(String userId) {
        JSONArray userArray=new JSONArray();
        if(null!=userId && userRepo.findById(Integer.valueOf(userId)).isPresent()) {
            User user = userRepo.findById(Integer.valueOf(userId)).get();
            JSONObject userObj = setUser(user);
            userArray.put(userObj);
        }else{
            List<User> userList= userRepo.findAll();
            for(User user:userList){
                JSONObject userObj=setUser(user);
                userArray.put(userObj);
            }
        }
        return userArray;
    }

    private JSONObject setUser(User user) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",user.getUserId());
        jsonObject.put("fName",user.getFName());
        jsonObject.put("lName",user.getLName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("number",user.getNumber());
        return jsonObject;
    }

    public void updateUser(User user, String userId) {
        if(userRepo.findById(Integer.valueOf(userId)).isPresent()){
            User user1= userRepo.findById(Integer.valueOf(userId)).get();
            user.setUserId(user1.getUserId());
            userRepo.save(user);
        }
    }
}
