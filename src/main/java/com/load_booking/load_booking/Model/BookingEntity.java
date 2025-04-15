package com.load_booking.load_booking.Model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String shipperId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "load_id", referencedColumnName = "id")
    private LoadEntity load;

    @Column(nullable =false)
    private String transporterId;
    @Column(nullable =false)
    private double proposedRate;
    @Column(nullable =false)
    private String comment;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    private Instant requestedAt;

    public enum BookingStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
