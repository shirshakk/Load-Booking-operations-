package com.load_booking.load_booking.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.load_booking.load_booking.Model.User;

public interface UserRepo extends JpaRepository<User,UUID>{
    Boolean findByEmailId(String emailId);
}
