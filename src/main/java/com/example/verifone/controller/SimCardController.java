package com.example.verifone.controller;

import com.example.verifone.payloads.ApiResponse;
import com.example.verifone.payloads.SimCardDto;
import com.example.verifone.services.impl.SimCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SimCardController {
    @Autowired
    private SimCardServiceImpl simCardServiceImpl;

    //GET - getting health check up of project
    @GetMapping("/")
    public ResponseEntity<ApiResponse> apiHealthCheckup(){
        return new ResponseEntity<>(new ApiResponse("Our Api project is running",true), HttpStatus.OK);
    }

    //GET - getting all the sim card detail records
    @GetMapping("/listall")
    public ResponseEntity<List<SimCardDto>> getAllSimCardDetailRecords(){
        List<SimCardDto> allSimCardDetailRecords =this.simCardServiceImpl.getAllSimDetailsRecords();
        return new ResponseEntity<>(allSimCardDetailRecords, HttpStatus.OK);
    }

    //GET - getting all the renewable sim card detail records
    @GetMapping("/to-renew")
    public ResponseEntity<List<SimCardDto>> getAllRenewableSimCardRecords(){
        List<SimCardDto> allRenewableSimCardRecords = this.simCardServiceImpl.getRenewableSimRecords();
        return new ResponseEntity<>(allRenewableSimCardRecords, HttpStatus.OK);
    }

    //POST - adding sim card detail record
    @PostMapping("/add")
    public ResponseEntity<SimCardDto> addSimCardDetailRecord(@RequestBody SimCardDto simCardDto){
        SimCardDto addedSimCardDetailRecord = this.simCardServiceImpl.addEntryOfSimDetails(simCardDto);
        return new ResponseEntity<>(addedSimCardDetailRecord, HttpStatus.OK);
    }

    //PUT - updating a sim card details by using sim id
    @PutMapping("/{simId}")
    public ResponseEntity<SimCardDto> updateSimCardDetailRecord(@RequestBody SimCardDto simCardDto, @PathVariable(name = "simId") long simId){
        SimCardDto updateSimCardDetailRecord = this.simCardServiceImpl.updateSimDetails(simCardDto, simId);
        return new ResponseEntity<>(updateSimCardDetailRecord, HttpStatus.OK);
    }

    //PUT - Updating the expiry date
    @PutMapping("/renew/{simId}")
    public ResponseEntity<SimCardDto> renewTheSimCard(@RequestBody SimCardDto simCardDto, @PathVariable(name = "simId") long simId){
        SimCardDto renewedSim = this.simCardServiceImpl.renewTheSim(simCardDto, simId);
        return new ResponseEntity<>(renewedSim, HttpStatus.OK);
    }

    //DELETE - deleting the sim card detail record by using the sim id
    @DeleteMapping("/{simId}")
    public ResponseEntity<ApiResponse> deleteSimCardDetailRecord(@PathVariable(name = "simId") long simId){
        this.simCardServiceImpl.deleteSimDetailRecord(simId);
        return new ResponseEntity<>(new ApiResponse("Sim card Detail deleted successfully", true), HttpStatus.OK);
    }
}
