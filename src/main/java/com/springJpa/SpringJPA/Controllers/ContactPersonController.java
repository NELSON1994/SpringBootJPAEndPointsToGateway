package com.springJpa.SpringJPA.Controllers;

import com.springJpa.SpringJPA.Entities.ContactPerson;
import com.springJpa.SpringJPA.Services.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@RestController
public class ContactPersonController {
@Autowired
    private ContactPersonService contactPersonService;
//create contact Person
    @PostMapping("/contactPerson/{id}")
    private ResponseEntity<ContactPerson> createContactPerson(@PathVariable Long id,
                                                              @RequestBody ContactPerson contactPerson){
        contactPersonService.create(id,contactPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactPerson);
    }

    // fetch contact persons
    @GetMapping("/contactPerson")
    private ResponseEntity<List<ContactPerson>> fetchAll(){
        List<ContactPerson> list=contactPersonService.fetchAllContactsPerson();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    // fetching one
    @GetMapping("/contactPerson/{id}")
    private ResponseEntity<ContactPerson> fetchOne(@PathVariable Long id){
        ContactPerson c=contactPersonService.fetchOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
// delete mapping
    @DeleteMapping("/contactPerson/{id}")
    private ResponseEntity<Map<String,String>> delete(@PathVariable Long id) throws ParseException {
        String rep=contactPersonService.deleteContactPerson(id);
        Map<String,String> map=new HashMap<>();
        if (rep.isEmpty()){
            HttpStatus  statuss=HttpStatus.NOT_FOUND;
            rep="NOT FOUND";
            map.put(rep,statuss.toString());
        }
        else{
            HttpStatus  statuss=HttpStatus.OK;
            rep="Deleted successfully";
            map.put(rep,statuss.toString());
        }
        return ResponseEntity.ok(map);
    }
}
