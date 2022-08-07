package com.example.verifone.services;

import com.example.verifone.payloads.SimCardDto;

import java.util.List;

public interface SimCardService {

    List<SimCardDto> getAllSimDetailsRecords();

    SimCardDto addEntryOfSimDetails(SimCardDto simCardDto);

    SimCardDto updateSimDetails(SimCardDto simCardDto, long simId);

    void deleteSimDetailRecord(long simId);

    List<SimCardDto> getRenewableSimRecords();

    SimCardDto renewTheSim(SimCardDto simCardDto, long simId);
}
