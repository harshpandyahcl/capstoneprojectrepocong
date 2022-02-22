package com.hcl.ppmtool;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class PpmtoolApplication {

    static  Logger logger = LogManager.getLogger(PpmtoolApplication.class.getName());
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {

        PropertyConfigurator.configure("C:\\Users\\James\\OneDrive\\Desktop\\Heroku Capstone\\Capstone2\\src\\main\\resources\\log4j.properties");
        logger.warn("DB accessed from Application..");
        SpringApplication.run(PpmtoolApplication.class, args);



    }

}
