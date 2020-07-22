package com.springJpa.SpringJPA.Services;

import com.springJpa.SpringJPA.Constants.Constants;
import com.springJpa.SpringJPA.Entities.HotelServicesOffered;
import com.springJpa.SpringJPA.Repositories.ServicesRepositories;
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
public class ServicesOffered {
    @Autowired
    private ServicesRepositories servicesRepositories;
    // ========================================= getting all service
    public List<HotelServicesOffered> findAllHotelServicesOffered(){
        return servicesRepositories.findAll();
    }
    // fetching approved service
    public List<HotelServicesOffered> fetchApprovedHotelServicesOffered(){
        List<HotelServicesOffered> hotels=servicesRepositories.findByActionStatus(Constants.Approved);
        return hotels;
    }
    // create HotelServicesOffered
    public HotelServicesOffered createHotelService(HotelServicesOffered hotel){
        String hotelCode;
        HotelServicesOffered hotel1=new HotelServicesOffered();
        Random random=new Random();
        int ran=random.nextInt(10000);
        String h1=hotel.getServiceName().replace(" ","").toUpperCase();
        if (h1.length()<5){hotelCode=h1.concat(String.valueOf(ran)); }
        else{ String gg=h1.substring(0,4);hotelCode=gg.concat(String.valueOf(ran)); }
        Date d=new Date();
        hotel1.setServiceName(hotel.getServiceName());
        hotel1.setServiceCode(hotelCode);
        hotel1.setActionStatus(Constants.UnApproved);
        hotel1.setAction(Constants.created);
        hotel1.setCreatedDate(d);
        hotel1.setUpdatedDate(d);
        servicesRepositories.save(hotel1);
        return hotel1;
    }
    // ========================================= getting an HotelServicesOffered
    public HotelServicesOffered getOneHotelServicesOffered(Long id){
        Optional<HotelServicesOffered> hotel=servicesRepositories.findById(id);
        if (!hotel.isPresent()){
            HotelServicesOffered hotelService=new HotelServicesOffered();
        }
        HotelServicesOffered hotelService=hotel.get();
        return hotelService;
    }
    //  =========================================delete an HotelServicesOffered
    public HotelServicesOffered deleteHotelServicesOffered(Long id){
        Optional<HotelServicesOffered> hotel=servicesRepositories.findById(id);
        HotelServicesOffered hotelService=null;
        if (hotel.isPresent()){
            hotelService=hotel.get();
            hotelService.setActionStatus(Constants.deleted);
            Date d=new Date();
            hotelService.setUpdatedDate(d);
            servicesRepositories.save(hotelService);

        }
        return hotelService;
    }

    // ========================================= update HotelServicesOffered
    public HotelServicesOffered updateHotelServicesOffered(Long id, HotelServicesOffered hotelService){
        String response;
        Optional<HotelServicesOffered> h=servicesRepositories.findById(id);
        if (h.isPresent()){
            hotelService=h.get();
            Date d=new Date();
            hotelService.setUpdatedDate(d);
            hotelService.setActionStatus(Constants.UnApproved);
            hotelService.setAction(Constants.updated);
            servicesRepositories.save(hotelService);
          //  response=hotelService.getServiceName() +"is updated succesful";
        }

        return hotelService;
    }
    // =========================================approve HotelServicesOffered
    public HotelServicesOffered approveHotelServicesOffered(Long id){
        Optional<HotelServicesOffered> h=servicesRepositories.findById(id);
        HotelServicesOffered hotelService = null;
        if (h.isPresent()){
            hotelService=h.get();
            hotelService.setActionStatus(Constants.Approved);
            Date d=new Date();
            hotelService.setUpdatedDate(d);
            servicesRepositories.save(hotelService);
        }
        return hotelService;
    }
    //  =========================================decline an HotelServicesOffered
    public HotelServicesOffered declineHotelServicesOffered(Long id){
        Optional<HotelServicesOffered> h=servicesRepositories.findById(id);
        HotelServicesOffered hotelService1=null;
        if (h.isPresent()){
            hotelService1=h.get();
            hotelService1.setActionStatus(Constants.declined);
            Date date=new Date();
            hotelService1.setUpdatedDate(date);
            servicesRepositories.save(hotelService1);

        }
        return hotelService1;
    }

    public HotelServicesOffered fetchByServiceCode(String serviceCode){
        HotelServicesOffered hotelServicesOfferedd=null;
        Optional<HotelServicesOffered> hotelServicesOffered=servicesRepositories.findHotelServicesOfferedByServiceCodeStartingWithAndActionStatus(serviceCode,Constants.Approved);
        if(hotelServicesOffered.isPresent()){hotelServicesOfferedd=hotelServicesOffered.get();}
        else{
            hotelServicesOfferedd=null;
        }
       return hotelServicesOfferedd;
    }
}
