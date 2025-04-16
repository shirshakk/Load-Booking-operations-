package com.load_booking.load_booking.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;

    @Column(nullable=false)
    private String userName;
    @Column(nullable=false)
    private String emailId;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private rolecategory role;
    
    public enum rolecategory{
        User,
        Admin
    }
}
