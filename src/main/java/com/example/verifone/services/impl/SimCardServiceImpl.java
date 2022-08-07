package com.example.verifone.services.impl;

import com.example.verifone.entities.SimCard;
import com.example.verifone.exceptions.ResourceNotFoundException;
import com.example.verifone.payloads.SimCardDto;
import com.example.verifone.repositories.SimCardRepository;
import com.example.verifone.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimCardServiceImpl implements SimCardService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SimCardRepository simCardRepository;

    @Override
    public List<SimCardDto> getAllSimDetailsRecords() {
        return this.simCardRepository.findAll().stream().map(this::simCardToDto).collect(Collectors.toList());
    }

    @Override
    public SimCardDto addEntryOfSimDetails(SimCardDto simCardDto) {
        SimCard simCardRecord = this.simCardDtoToSimCard(simCardDto);
        return this.simCardToDto(this.simCardRepository.save(simCardRecord));
    }

    @Override
    public SimCardDto updateSimDetails(SimCardDto simCardDto, long simId) {
        SimCard simCardRecord = this.simCardRepository.findById(simId).orElseThrow(()-> new ResourceNotFoundException("Sim Card Record", "ID: ", simId));
        simCardRecord.setSimCardNo(simCardDto.getSimCardNo());
        simCardRecord.setMobileNo(simCardDto.getMobileNo());
        simCardRecord.setStatus(simCardDto.getStatus());
        simCardRecord.setExpiryDate(simCardDto.getExpiryDate());
        simCardRecord.setStateOfRegistration(simCardDto.getStateOfRegistration());
        simCardRecord.setKyc(simCardDto.isKyc());
        simCardRecord.setTelecomProvider(simCardDto.getTelecomProvider());
        simCardRecord.setFullName(simCardDto.getFullName());
        return this.simCardToDto(this.simCardRepository.save(simCardRecord));
    }

    @Override
    public void deleteSimDetailRecord(long simId) {
        SimCard simCardRecord = this.simCardRepository.findById(simId).orElseThrow(()-> new ResourceNotFoundException("Sim Card Record", "ID: ", simId));
        this.simCardRepository.delete(simCardRecord);
    }

    @Override
    public List<SimCardDto> getRenewableSimRecords() {
        List<SimCardDto> allSimCardRecords  = this.simCardRepository.findAll().stream().map(this::simCardToDto).collect(Collectors.toList());
        return allSimCardRecords.stream().filter(simCardRecord -> Duration.between( LocalDateTime.now(), simCardRecord.getExpiryDate()).toDays() < 30 ).collect(Collectors.toList());
    }

    @Override
    public SimCardDto renewTheSim(SimCardDto simCardDto, long simId) {
        SimCard simCardRecord = this.simCardRepository.findById(simId).orElseThrow(()-> new ResourceNotFoundException("Sim Card Record ", "Id: ", simId));
        simCardRecord.setSimCardNo(simCardRecord.getSimCardNo());
        simCardRecord.setMobileNo(simCardRecord.getMobileNo());
        simCardRecord.setStatus(simCardRecord.getStatus());
        simCardRecord.setExpiryDate(simCardDto.getExpiryDate());
        simCardRecord.setStateOfRegistration(simCardRecord.getStateOfRegistration());
        simCardRecord.setKyc(simCardRecord.isKyc());
        simCardRecord.setTelecomProvider(simCardRecord.getTelecomProvider());
        simCardRecord.setFullName(simCardRecord.getFullName());
        return this.simCardToDto(this.simCardRepository.save(simCardRecord));
    }

    public SimCardDto simCardToDto(SimCard simCard){
        return this.modelMapper.map(simCard, SimCardDto.class);
    }

    public SimCard simCardDtoToSimCard(SimCardDto simCardDto){
        return this.modelMapper.map(simCardDto, SimCard.class);
    }
}
