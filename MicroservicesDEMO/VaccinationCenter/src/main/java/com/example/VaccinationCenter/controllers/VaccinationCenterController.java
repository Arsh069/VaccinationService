package com.example.VaccinationCenter.controllers;

import com.example.CitizenService.entity.Citizen;
import com.example.VaccinationCenter.entity.RequiredResponse;
import com.example.VaccinationCenter.entity.VaccinationCenter;
import com.example.VaccinationCenter.repository.CenterRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

    @Autowired
    CenterRepo centerRepo;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path = "/add")
    public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter newVaccinationCenter) {
        return new ResponseEntity<>(centerRepo.save(newVaccinationCenter), HttpStatus.OK);
    }

    @GetMapping(path = "id/{id}")
    @CircuitBreaker(name = "citizenService", fallbackMethod = "getDetailsOnFailure")
    public ResponseEntity<RequiredResponse> getAllDetails(@PathVariable Integer id) {
        RequiredResponse requiredResponse = new RequiredResponse();

        // Get vaccination center detail
        VaccinationCenter vaccinationCenterDetail = centerRepo.findById(id).orElse(null);
        if (vaccinationCenterDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requiredResponse.setCenter(vaccinationCenterDetail);

        // Get all citizen details
        List<Citizen> citizens = restTemplate.getForObject("http://CITIZENSERVICE/citizen/id/" + id, List.class);
        requiredResponse.setCitizen(citizens);

        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
    }

    public ResponseEntity<RequiredResponse> getDetailsOnFailure(@PathVariable Integer id, Throwable throwable) {
        RequiredResponse requiredResponse = new RequiredResponse();

        // Get vaccination center detail
        VaccinationCenter vaccinationCenterDetail = centerRepo.findById(id).orElse(null);
        if (vaccinationCenterDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requiredResponse.setCenter(vaccinationCenterDetail);

        // Fallback method does not provide citizen details
        requiredResponse.setCitizen(null);

        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
    }

}
