package com.springJpa.SpringJPA.Services;

import com.springJpa.SpringJPA.Constants.Constants;
import com.springJpa.SpringJPA.Entities.ContactPerson;
import com.springJpa.SpringJPA.Entities.Hotel;
import com.springJpa.SpringJPA.Repositories.ContactPersonRepository;
import com.springJpa.SpringJPA.Repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class ContactPersonService {
    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HotelService hotelService;

    //create contact person
    public ContactPerson create(Long id, ContactPerson contactPerson) {
        Hotel h = hotelService.getOneHotel(id);
        if (h == null) {
            hotelService.createHotel(h);
        }
        Date d = new Date();
        String password= contactPerson.getPassword();
        String encodedPassword=passwordEncoder.encode(password);
        System.out.println("==================password 1" +password);
        System.out.println("==================password 2" +encodedPassword);
        contactPerson.setPassword(encodedPassword);
        contactPerson.setFname(contactPerson.getFname().toUpperCase());
        contactPerson.setHotel(h);
        contactPerson.setActionStatus(Constants.UnApproved);
        contactPerson.setAction(Constants.created);
        contactPerson.setUpdateDate(d);
        contactPerson.setCreationDate(d);
        contactPersonRepository.save(contactPerson);
        return contactPerson;

    }
// fetching all contact persons
    public List<ContactPerson> fetchAllContactsPerson(){
        List<ContactPerson> contactPeople=contactPersonRepository.findAll();
        return contactPeople;
    }

  // fetching one
    public ContactPerson fetchOne(Long id){
        ContactPerson contactPerson1= new ContactPerson();
        Optional<ContactPerson> contactPerson=contactPersonRepository.findById(id);
        if (contactPerson.isPresent()){
            return contactPerson.get();
        }
        else {
        return contactPerson1;
        }

    }

    // delete contact person
    public String deleteContactPerson(Long id) throws ParseException {
        String response=null;
        // find it first
        Optional<ContactPerson> contactPerson=contactPersonRepository.findById(id);
        if (contactPerson.isPresent()){
            ContactPerson contactPerson1=contactPerson.get();
            Date date=new Date();
            SimpleDateFormat formatt=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String ddate=formatt.format(date);
            Date date1=formatt.parse(ddate);
            System.out.println("================date=================  " +ddate);
            System.out.println("================date2=================  " +date1);
            contactPerson1.setActionStatus(Constants.deleted);
            contactPerson1.setUpdateDate(date1);
            contactPersonRepository.save(contactPerson1);

            response="ContactPerson " +contactPerson1.getFname().concat(" ") +contactPerson1.getLname() +"deleted successfully";
        }
        else {
            response="";
        }

        return  response;
    }

}
