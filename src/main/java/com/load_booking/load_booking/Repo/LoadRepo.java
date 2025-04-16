package com.load_booking.load_booking.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.load_booking.load_booking.Model.LoadEntity;

public interface LoadRepo extends JpaRepository<LoadEntity,UUID>{
}
