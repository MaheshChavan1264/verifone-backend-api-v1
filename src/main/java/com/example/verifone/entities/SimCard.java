package com.example.verifone.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "simdetails")
@Getter
@Setter
@NoArgsConstructor
public class SimCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long simCardNo;
    private long mobileNo;
    private String status;
    private LocalDateTime expiryDate;
    private String stateOfRegistration;
    private boolean kyc;
    private String telecomProvider;
    private String fullName;
}
