package com.springJpa.SpringJPA.Controllers;

import com.springJpa.SpringJPA.Entities.Hotel;
import com.springJpa.SpringJPA.Entities.HotelServicesOffered;
import com.springJpa.SpringJPA.Services.HotelService;
import com.springJpa.SpringJPA.Services.ServicesOffered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@RestController
public class ServicesController {
    @Autowired
    private ServicesOffered servicesOffered;
    //create services
    @PostMapping("/hotelService")
    private ResponseEntity<HotelServicesOffered> create(@RequestBody HotelServicesOffered hotelServicesOffered){
        HotelServicesOffered hotelServicesOffered1=servicesOffered.createHotelService(hotelServicesOffered);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServicesOffered1);
    }
    //fetch all
    @GetMapping("/hotelService")
    private ResponseEntity<List<HotelServicesOffered>> fetchAll(){
        List<HotelServicesOffered> lists=servicesOffered.findAllHotelServicesOffered();
        if (lists==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(lists);
    }
    //fetch one by id
    @GetMapping("/hotelService/id/{id}")
    private ResponseEntity<HotelServicesOffered> fetchOne(@PathVariable Long id){
        HotelServicesOffered hotelServicesOffered=servicesOffered.getOneHotelServicesOffered(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelServicesOffered);
    }
    //fetch by service code
    @GetMapping("/hotelService/code/{code}")
    private ResponseEntity<HotelServicesOffered> fetchOneByServiceCode(@PathVariable String code){
        HotelServicesOffered hotelServicesOffered=servicesOffered.fetchByServiceCode(code.toUpperCase());
        if (hotelServicesOffered==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(hotelServicesOffered);
    }
    // update service
    @PutMapping("/hotelService")
    private ResponseEntity<HotelServicesOffered> updateService(@PathVariable Long id,@RequestBody HotelServicesOffered hotelServicesOffered){
        HotelServicesOffered gg=servicesOffered.updateHotelServicesOffered(id,hotelServicesOffered);
        return ResponseEntity.status(HttpStatus.OK).body(gg);
    }
    //delete service
    @DeleteMapping("/hotelService/{id}")
    private ResponseEntity<HotelServicesOffered> deleteService(@PathVariable Long id){
        HotelServicesOffered hotelServicesOffered=servicesOffered.deleteHotelServicesOffered(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelServicesOffered);
    }
    //approve service
    @PutMapping("/hotelService/approve/{id}")
    private ResponseEntity<HotelServicesOffered> approveAction(@PathVariable Long id){
        HotelServicesOffered hotelService=servicesOffered.approveHotelServicesOffered(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelService);
    }
    //decline service
    @PutMapping("/hotelService/decline/{id}")
    private ResponseEntity<HotelServicesOffered> declineAction(@PathVariable Long id){
        HotelServicesOffered hotelService=servicesOffered.declineHotelServicesOffered(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelService);
    }
}
