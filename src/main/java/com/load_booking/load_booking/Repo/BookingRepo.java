package com.load_booking.load_booking.Repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.load_booking.load_booking.Model.BookingEntity;
import com.load_booking.load_booking.Model.LoadEntity;

@Repository

public interface BookingRepo extends JpaRepository<BookingEntity,UUID>{
    Optional<BookingEntity> findByLoad(LoadEntity load);
    Boolean  existsByLoad_Id(UUID id);
}
