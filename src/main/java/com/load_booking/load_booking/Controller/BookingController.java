package com.load_booking.load_booking.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.load_booking.load_booking.Model.BookingEntity;
import com.load_booking.load_booking.Service.BookingService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> addBooking(@RequestBody BookingEntity newBooking) {
        try{
            bookingService.addBooking(newBooking);
            return ResponseEntity.ok().body("Booking added");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to add Booking");
        }
    }

    @GetMapping("/booking")
    public ResponseEntity<List<BookingEntity>> getBookingDetails(@RequestParam(required = false) String shipperId,@RequestParam(required = false) String transporterId) {
        try{
            List<BookingEntity> bookingDetails=bookingService.getBookingDetails(shipperId,transporterId);
            return ResponseEntity.ok(bookingDetails);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<BookingEntity> getBookingById(@PathVariable UUID bookingId) {
        try{
            BookingEntity bookingDetail=bookingService.getBookingById(bookingId);
            return ResponseEntity.ok(bookingDetail);
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("booking/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID bookingId){
        try{
            bookingService.deleteBooking(bookingId);
            return ResponseEntity.ok().body("Booking Deleted");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to delete booking");
        }
    }
    
    
    
}
