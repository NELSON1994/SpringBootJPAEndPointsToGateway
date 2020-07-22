package com.springJpa.SpringJPA.Controllers;

import com.springJpa.SpringJPA.Entities.Hotel;
import com.springJpa.SpringJPA.Services.HotelService;
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
public class HotelController {
    @Autowired
    private HotelService hotelService;
    ////==========================getting all hotels
    @GetMapping("/hotel")
    private ResponseEntity<List<Hotel>> fetchHotels(){
        List<Hotel> hotel=hotelService.findAllHotels();
        return ResponseEntity.ok(hotel);
    }
    ////==========================create hotel
    @PostMapping("/hotel")
    private ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1=hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }
    ////==========================get hotel by id
    @GetMapping("/hotel/{id}")
    private ResponseEntity<Hotel> findHotelById(@PathVariable Long id){
        Hotel hotel=hotelService.getOneHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }
    ////==========================approve hotel
    @PutMapping("/hotel/approve/{id}")
    private ResponseEntity<Hotel> approveAction(@PathVariable Long id){
        Hotel h=hotelService.approveHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body(h);
    }
    ////==========================decline hotel
    @PutMapping("/hotel/decline/{id}")
    private ResponseEntity<Hotel> declineAction(@PathVariable Long id){
        Hotel hotel=hotelService.declineHotel(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }
    ////==========================delete hotel
    @DeleteMapping("hotel/{id}")
    private ResponseEntity<String> deleteHotel(@PathVariable Long id){
        String vv=hotelService.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    ////==========================update hotel
    @PutMapping("/hotel/{id}")
    private ResponseEntity<String> updateHotel(@PathVariable Long id,@RequestBody Hotel hotel){
        String bb=hotelService.updateHotel(id,hotel);
        return ResponseEntity.status(HttpStatus.OK).body(bb);
    }
    ////========================== fetch list of approve hotels
    @GetMapping("/hotel/approved")
    private ResponseEntity<List<Hotel>> findApprovedHotels(){
        List<Hotel> hotel=hotelService.fetchApprovedHotels();
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }
}
