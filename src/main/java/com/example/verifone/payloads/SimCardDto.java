package com.example.verifone.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SimCardDto {
    private long id;
    private long simCardNo;
    private long mobileNo;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryDate;
    private String stateOfRegistration;
    private boolean kyc;
    private String telecomProvider;
    private String fullName;
}
