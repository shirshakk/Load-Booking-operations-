package com.load_booking.load_booking.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "UserData")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(nullable=false)
    private String userName;
    @Column(nullable=false)
    private String emailId;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Rolecategory role;
    
    public enum Rolecategory{
        User,
        Admin
    }
}
