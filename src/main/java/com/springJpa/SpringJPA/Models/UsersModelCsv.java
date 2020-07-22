package com.springJpa.SpringJPA.Models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Data
public class UsersModelCsv {
    @CsvBindByName  // used to bind name on csv file to column name(HEADER)
    private long id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String email;
    @CsvBindByName(column = "country")
    private String countryCode;
    @CsvBindByName
    private int age;
}
