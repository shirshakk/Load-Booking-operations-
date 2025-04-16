package com.load_booking.load_booking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.load_booking.load_booking.Model.UserDetailImplementation;

import com.load_booking.load_booking.Model.User;
import com.load_booking.load_booking.Repo.UserRepo;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userRepo.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetailImplementation(user);
    }
}