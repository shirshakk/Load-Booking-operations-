package com.load_booking.load_booking.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.load_booking.load_booking.Model.User;
import com.load_booking.load_booking.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> AddNewUser(@RequestBody User newUser) {
        try{
            userService.addNewUsed(newUser);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to create new user");
        }
    }
    
}
