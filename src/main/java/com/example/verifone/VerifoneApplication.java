package com.example.verifone;

import com.example.verifone.entities.SimCard;
import com.example.verifone.repositories.SimCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
@EnableSwagger2
public class VerifoneApplication implements CommandLineRunner {
    @Autowired
    private SimCardRepository simCardRepository;

    public static void main(String[] args) {
        SpringApplication.run(VerifoneApplication.class, args);
    }
    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
       LocalDateTime expiryDate = LocalDateTime.of(2022, 10, 30, 23, 59);
        SimCard addFirstRecord = new SimCard();
        addFirstRecord.setId(0);
        addFirstRecord.setSimCardNo(12345667890L);
        addFirstRecord.setMobileNo(9090909090L);
        addFirstRecord.setStatus("Active");
        addFirstRecord.setExpiryDate(expiryDate);
        addFirstRecord.setStateOfRegistration("Maharashtra");
        addFirstRecord.setKyc(true);
        addFirstRecord.setTelecomProvider("Airtel");
        addFirstRecord.setFullName("Jammy Stark");
        SimCard addedRecord = this.simCardRepository.save(addFirstRecord);
        System.out.println("Sim card Detail Record is added"+addedRecord);
        System.out.println("Hello welcome to the spring boot application");
    }
}
