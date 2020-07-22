package com.springJpa.SpringJPA.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */

@Entity
@Data
//@Table(name = "services")
public class HotelServicesOffered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serviceName;
    private String serviceCode;
    private String action;
    private String actionStatus;
    private Date createdDate;
    private Date updatedDate;

    public HotelServicesOffered() {
    }

    public HotelServicesOffered(String serviceName, String serviceCode, String action, String actionStatus, Date createdDate, Date updatedDate) {
        this.serviceName = serviceName;
        this.serviceCode = serviceCode;
        this.action = action;
        this.actionStatus = actionStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
