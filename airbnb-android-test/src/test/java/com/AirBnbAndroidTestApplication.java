package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hackathon", "com.airbnb"})
public class AirBnbAndroidTestApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AirBnbAndroidTestApplication.class, args);
    }
}
