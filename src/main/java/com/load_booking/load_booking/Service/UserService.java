package com.load_booking.load_booking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.load_booking.load_booking.Exception.LoadValidationException;
import com.load_booking.load_booking.Model.User;
import com.load_booking.load_booking.Repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User addNewUsed(User newUser){
        if (userRepo.findByEmailId(newUser.getEmailId()).isPresent()) {
            throw new LoadValidationException("User already exists");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }
}
