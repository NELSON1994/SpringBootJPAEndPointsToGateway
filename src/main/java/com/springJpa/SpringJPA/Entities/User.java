package com.springJpa.SpringJPA.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  name;
    private String email;
    private String countryCode;
    private int age;

    public User() {
    }

    public User(String name, String email, String countryCode, int age) {
        this.name = name;
        this.email = email;
        this.countryCode = countryCode;
        this.age = age;
    }
}
