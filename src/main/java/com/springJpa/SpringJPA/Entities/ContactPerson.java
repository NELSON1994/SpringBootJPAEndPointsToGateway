package com.springJpa.SpringJPA.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContactPerson {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String fname;
        private String lname;
        private int idNo;
        private String email;
        private String gender;
        private String username;
        private String password;
        private String action;
        private String actionStatus;
        private Date creationDate;
        private Date updateDate;
        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY,optional = false)
        @JoinColumn(name="hotel_id",nullable = false)
        private Hotel hotel;

    public ContactPerson(String fname, Hotel hotel,String lname, int idNo, String email, String gender, String username, String password, String action, String actionStatus, Date creationDate, Date updateDate) {
        this.fname = fname.toUpperCase();
        this.lname = lname.toUpperCase();
        this.idNo = idNo;
        this.email = email;
        this.gender = gender.toUpperCase();
        this.username = username;
        this.password = password;
        this.action = action;
        this.actionStatus = actionStatus;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.hotel=hotel;
    }
}
