package com.springJpa.SpringJPA.Services;

import com.springJpa.SpringJPA.Constants.Constants;
import com.springJpa.SpringJPA.Entities.Hotel;
import com.springJpa.SpringJPA.Repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    // ========================================= getting all hotels
    public List<Hotel> findAllHotels(){
        return hotelRepository.findAll();
    }
    // fetching approved hotels
    public List<Hotel> fetchApprovedHotels(){
        List<Hotel> hotels=hotelRepository.findByActionStatus(Constants.Approved);
        return hotels;
    }

    // create hotel
    public Hotel createHotel(Hotel hotel){
        String hotelCode;
        Hotel hotel1=new Hotel();
        Random random=new Random();
        int ran=random.nextInt(10000);
        String h1=hotel.getHotelName().replace(" ","").toUpperCase();
        if (h1.length()<5){hotelCode=h1.concat(String.valueOf(ran)); }
        else{ String gg=h1.substring(0,4);hotelCode=gg.concat(String.valueOf(ran)); }
       Date d=new Date();
        hotel1.setHotelName(hotel.getHotelName());
        hotel1.setServices(hotel.getServices());
        hotel1.setHotelDescriptions(hotel.getHotelDescriptions());
        hotel1.setHotelCode(hotelCode);
       hotel1.setActionStatus(Constants.UnApproved);
       hotel1.setAction(Constants.created);
       hotel1.setUpdateDate(d);
       hotel1.setCreationDate(d);
      hotelRepository.save(hotel1);
      return hotel1;
    }
    // ========================================= getting an hotel
    public Hotel getOneHotel(Long id){
        Optional<Hotel> hotel=hotelRepository.findById(id);
        if (!hotel.isPresent()){
            Hotel hotel1=new Hotel();
            return hotel1;
        }
        return hotel.get();
    }
    //  =========================================delete an hotel
    public String deleteHotel(Long id){
        Optional<Hotel> hotel=hotelRepository.findById(id);
        String message="";
        if (hotel.isPresent()){
            Hotel hotel1=hotel.get();
            hotel1.setActionStatus(Constants.deleted);
            Date d=new Date();
            hotel1.setUpdateDate(d);
            hotelRepository.save(hotel1);
            message=hotel1.getHotelName()+"is succesfully deleted";

        }
        else{
            message="Hotel not found";
        }
        return message;
    }

    // ========================================= update hotel
    public String updateHotel(Long id,Hotel hotel){
    String response;
    Optional<Hotel> h=hotelRepository.findById(id);
    if (h.isPresent()){
    hotel=h.get();
    Date d=new Date();
    hotel.setUpdateDate(d);
    hotel.setActionStatus(Constants.UnApproved);
    hotel.setAction(Constants.updated);
   // hotel.setHotelCode(hotel.getHotelCode());
    hotelRepository.save(hotel);
    response=hotel.getHotelName() +"is updated succesful";
        }
    else{
        response="Hotel not updated";
     }
        return response;
    }
    // =========================================approve hotel
    public Hotel approveHotel(Long id){
        Optional<Hotel> h=hotelRepository.findById(id);
        Hotel hotel = null;
        if (h.isPresent()){
            hotel=h.get();
           hotel.setActionStatus(Constants.Approved);
            Date d=new Date();
            hotel.setUpdateDate(d);
            hotelRepository.save(hotel);
        }
        return hotel;
    }
    //  =========================================decline an hotel
    public Hotel declineHotel(Long id){
        Optional<Hotel> h=hotelRepository.findById(id);
        Hotel hotel1=null;
        if (h.isPresent()){
            hotel1=h.get();
            hotel1.setActionStatus(Constants.declined);
            Date date=new Date();
            hotel1.setUpdateDate(date);
            hotelRepository.save(hotel1);

        }
        return hotel1;
    }
}
