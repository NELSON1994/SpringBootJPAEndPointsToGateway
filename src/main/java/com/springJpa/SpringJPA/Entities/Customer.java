package com.springJpa.SpringJPA.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Customer {
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

    public Customer(String fname, String lname, int idNo, String email, String gender, String username, String password, String action, String actionStatus, Date creationDate, Date updateDate) {
        this.fname = fname;
        this.lname = lname;
        this.idNo = idNo;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.action = action;
        this.actionStatus = actionStatus;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
}
