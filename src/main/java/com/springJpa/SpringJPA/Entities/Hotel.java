package com.springJpa.SpringJPA.Entities;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String hotelName;
    private String hotelCode;
    private String hotelDescriptions;
    private String Services;
    private Date creationDate;
    private Date updateDate;
    private String action;
    @Column(name="action_status")
    private String actionStatus;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "hotel")
    private Set<ContactPerson> contactPerson;

    public Hotel() {
    }

    public Hotel(String hotelName, String hotelCode, String hotelDescriptions, String services, Date creationDate, Date updateDate, String action, String actionStatus) {
        this.hotelName = hotelName;
        this.hotelCode = hotelCode;
        this.hotelDescriptions = hotelDescriptions;
        Services = services;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.action = action;
        this.actionStatus = actionStatus;
    }

}
