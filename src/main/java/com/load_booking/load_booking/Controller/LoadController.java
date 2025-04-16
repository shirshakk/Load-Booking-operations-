package com.load_booking.load_booking.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.load_booking.load_booking.Model.LoadEntity;
import com.load_booking.load_booking.Service.LoadService;

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
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class LoadController {
    @Autowired
    private LoadService loadService;
    @PostMapping("/load")
    public ResponseEntity<?> NewLoad(@RequestBody LoadEntity newLoad) {
        try{
            loadService.addNewLoad(newLoad);
            return ResponseEntity.ok().body("New Record Saved");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Check the details");
        }
    }

    @GetMapping("/load")
    public ResponseEntity<List<LoadEntity>> fetchLoadByFilter(@RequestParam(required = false) String shipperId, @RequestParam(required = false) String transporterId) {
        List<LoadEntity> loads=loadService.getFilteredLoad(shipperId,transporterId);
        if(loads==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/load/{loadId}")
    public ResponseEntity<?> fetchLoadById(@PathVariable UUID loadId) {
        
        LoadEntity load=loadService.getLoadById(loadId);
        if(load==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(load);
        
    }
    
    @PutMapping("/load/{loadId}")
    public ResponseEntity<?> updateLoadDetails(@PathVariable UUID loadId,@RequestBody LoadEntity updatedLoad) {
        try{
            loadService.UpdateLoadDetails(loadId,updatedLoad);
            return ResponseEntity.ok().body("Load Updated Successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to update"+e.getMessage());
        }
    }
    
    @DeleteMapping("/load/{loadId}")
    public ResponseEntity<?> DeleteLoadDetails(@PathVariable UUID loadId){
        try{
            loadService.DeleteLoadDetail(loadId);
            return ResponseEntity.ok().body("Load details have beed deleted");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to delete the load details");
        }
    }
    
}
