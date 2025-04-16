package com.load_booking.load_booking.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.load_booking.load_booking.Exception.LoadValidationException;
import com.load_booking.load_booking.Model.BookingEntity;
import com.load_booking.load_booking.Model.LoadEntity;
import com.load_booking.load_booking.Model.LoadEntity.LoadStatus;
import com.load_booking.load_booking.Repo.BookingRepo;
import com.load_booking.load_booking.Repo.LoadRepo;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private LoadRepo loadRepo;

    public BookingEntity addBooking(BookingEntity newBooking){
        UUID loadId = newBooking.getLoad().getId();
        LoadEntity load = loadRepo.findById(loadId)
            .orElseThrow(() -> new LoadValidationException("Load not found"));
    
        if (bookingRepo.existsByLoad_Id(loadId)) {
            throw new LoadValidationException("Booking has already been done");
        }
    
        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new LoadValidationException("Cannot book a cancelled load");
        }
    
        load.setStatus(LoadStatus.BOOKED);
    
        BookingEntity bookedEntity = bookingRepo.save(newBooking);
        loadRepo.save(load);
    
        return bookedEntity;
    }
    

    public List<BookingEntity> getBookingDetails(String shipperId, String transporterId){
        List<BookingEntity> bookedDetails=bookingRepo.findAll();
        return bookedDetails.stream()
        .filter(data -> shipperId == null || 
                        (data.getLoad() != null && 
                         data.getLoad().getShipperId().equalsIgnoreCase(shipperId)))
        .filter(data -> transporterId == null || 
                        data.getTransporterId().equalsIgnoreCase(transporterId))
        .collect(Collectors.toList());
    }

    public BookingEntity getBookingById(UUID bookingId){
        return bookingRepo.findById(bookingId).orElseThrow(()->new LoadValidationException("Check the booking Id"));
    }

    public BookingEntity deleteBooking(UUID bookingId){
        BookingEntity data=bookingRepo.findById(bookingId).orElseThrow(() -> new LoadValidationException("Booking not found"));
        bookingRepo.delete(data);
        LoadEntity load = data.getLoad();
        load.setStatus(LoadStatus.CANCELLED);
        loadRepo.save(load);
        return data;
    }

    public BookingEntity acceptBooking(UUID bookingId) {
        BookingEntity booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new LoadValidationException("Booking not found"));
    
        booking.setStatus(BookingEntity.BookingStatus.ACCEPTED);
        return bookingRepo.save(booking);
    }

}