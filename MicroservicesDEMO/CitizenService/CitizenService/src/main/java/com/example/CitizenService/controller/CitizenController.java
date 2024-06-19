package com.example.CitizenService.controller;

import com.example.CitizenService.entity.Citizen;
import com.example.CitizenService.repository.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/citizen")
public class CitizenController {

    CitizenRepo citizenRepo;
    @Autowired
    CitizenController(CitizenRepo citizenRepo){
        this.citizenRepo=citizenRepo;
    }

    @RequestMapping(path = "/id/{id}")
    public ResponseEntity<List<Citizen>> getCitizens(@PathVariable Integer id){
        List<Citizen> listCitizen = citizenRepo.findByVaccinationCenterId(id);
        return new ResponseEntity<>(listCitizen, HttpStatus.OK);
    }


    @PostMapping(path = "/add")
    public Citizen addCitizen(@RequestBody Citizen newCitizen){
        return citizenRepo.save(newCitizen);
    }
}
