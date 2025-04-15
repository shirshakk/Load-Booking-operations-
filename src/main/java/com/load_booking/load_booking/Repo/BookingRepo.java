package com.load_booking.load_booking.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.load_booking.load_booking.Model.BookingEntity;

@Repository

public interface BookingRepo extends JpaRepository<BookingEntity,UUID>{
    
}
