package com.load_booking.load_booking.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.load_booking.load_booking.Model.BookingEntity;
import com.load_booking.load_booking.Model.LoadEntity;
import com.load_booking.load_booking.Repo.BookingRepo;
import com.load_booking.load_booking.Repo.LoadRepo;
import com.load_booking.load_booking.Exception.LoadValidationException;

@Service
public class LoadService {
    @Autowired
    private LoadRepo loadRepo;

    @Autowired
    private BookingRepo bookingRepo;

    public LoadEntity addNewLoad(LoadEntity newLoad) {
        validateLoad(newLoad);
        try {
            return loadRepo.save(newLoad);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save load: " + e.getMessage());
        }
    }
    public List<LoadEntity> getFilteredLoad(String shipperId,String truckType){
        List<LoadEntity> loads=loadRepo.findAll();
        return loads.stream()
        .filter(load->shipperId==null || load.getShipperId().equalsIgnoreCase(shipperId))
        .filter(load -> truckType == null || load.getTruckType().equalsIgnoreCase(truckType))
        .collect(Collectors.toList());
    }

    public LoadEntity getLoadById(UUID loadId){
        return loadRepo.findById(loadId).orElseThrow(() -> new LoadValidationException("Load not found with ID: " + loadId));
    }

    public LoadEntity UpdateLoadDetails(UUID id, LoadEntity updatedLoad){
        LoadEntity load;
        try {
            load = loadRepo.getReferenceById(id);
        } catch (Exception e) {
            throw new LoadValidationException("Data not found");
        }
        if(updatedLoad.getFacility()!=null){
            load.setFacility(updatedLoad.getFacility());
        }
        if(updatedLoad.getTruckType()!=null){
            load.setTruckType(updatedLoad.getTruckType());
        }
        if(updatedLoad.getNoOfTrucks()>0){
            load.setNoOfTrucks(updatedLoad.getNoOfTrucks());
        }
        if(updatedLoad.getWeight()>0){
            load.setWeight(updatedLoad.getWeight());
        }
        if(updatedLoad.getComment()!=null){
            load.setComment(updatedLoad.getComment());
        }
        if(updatedLoad.getStatus()== LoadEntity.LoadStatus.CANCELLED){
            load.setStatus(LoadEntity.LoadStatus.CANCELLED);
        }
        return load;
    }
    public LoadEntity DeleteLoadDetail(UUID loadId){
        LoadEntity load;
        try{
            load=loadRepo.getReferenceById(loadId);
        }catch(Exception e){
            throw new LoadValidationException("Data not found");
        }
        loadRepo.deleteById(loadId);
        return load;
    }

    private void validateLoad(LoadEntity load) {
        if (load == null) {
            throw new LoadValidationException("Load cannot be null");
        }
        if (load.getShipperId() == null || load.getShipperId().trim().isEmpty()) {
            throw new LoadValidationException("Shipper ID is required");
        }
        if (load.getFacility() == null) {
            throw new LoadValidationException("Facility details are required");
        }
        if (load.getFacility().getLoadingPoint() == null || load.getFacility().getLoadingPoint().trim().isEmpty()) {
            throw new LoadValidationException("Loading point is required");
        }
        if (load.getFacility().getUnloadingPoint() == null || load.getFacility().getUnloadingPoint().trim().isEmpty()) {
            throw new LoadValidationException("Unloading point is required");
        }
        if (load.getNoOfTrucks() <= 0) {
            throw new LoadValidationException("Number of trucks must be greater than 0");
        }
        if (load.getWeight() <= 0) {
            throw new LoadValidationException("Weight must be greater than 0");
        }
    }
}