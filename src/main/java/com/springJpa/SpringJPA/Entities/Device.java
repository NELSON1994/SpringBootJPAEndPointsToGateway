package com.springJpa.SpringJPA.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @Aurhor : Nelson
 *
 */


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serialNumber;
    private String Model;
    private String action;
    private String actionStatus;
    private Date creationDate;
    private Date updateDate;

    public Device(String serialNumber, String model, String action, String actionStatus, Date creationDate, Date updateDate) {
        this.serialNumber = serialNumber;
        Model = model;
        this.action = action;
        this.actionStatus = actionStatus;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
}
