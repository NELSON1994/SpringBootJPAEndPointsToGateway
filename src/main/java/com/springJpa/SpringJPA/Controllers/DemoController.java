package com.springJpa.SpringJPA.Controllers;

import com.springJpa.SpringJPA.Configs.NelsConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Aurthor :Nelson Mose
 * @Date:22:07:20202
 */
@RestController
// demo on fetching values from application properties file
public class DemoController {

    @Autowired
    private NelsConfigProperties nelsConfigsProperties;

    @Value("${my.greeting}")// getting value from application properties
    private String message1;

    @GetMapping("/greeting")
    public String hello(){
        String message;
        return message1;
    }
    @GetMapping("/propertiesDetails")
    public String results(){
        return "NAME :" +nelsConfigsProperties.getName()+" Age :" + nelsConfigsProperties.getAge() +" \njob :" +nelsConfigsProperties.getJob();
    }
}

