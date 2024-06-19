package com.example.VaccinationCenter.repository;

import com.example.VaccinationCenter.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepo extends JpaRepository<VaccinationCenter,Integer> {
}
