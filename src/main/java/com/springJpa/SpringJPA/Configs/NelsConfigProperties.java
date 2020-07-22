package com.springJpa.SpringJPA.Configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("nel")
public class NelsConfigProperties {
    private String name;
    private int age;
    private String job;
}
