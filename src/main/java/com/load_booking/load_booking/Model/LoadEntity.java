package com.load_booking.load_booking.Model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String shipperId;
    @Embedded
    private Facility facility;

    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;
    private String comment;

    private Instant datePosted;

    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.POSTED;

    public enum LoadStatus {
        POSTED,
        BOOKED,
        CANCELLED
    }

    @Embeddable
    @Data
    public static class Facility {
        private String loadingPoint;
        private String unloadingPoint;
        private Instant loadingDate;
        private Instant unloadingDate;
    }
}
