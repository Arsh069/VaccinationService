package com.example.VaccinationCenter.entity;

import com.example.CitizenService.entity.Citizen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {
    private VaccinationCenter center;
    private List<Citizen> citizen;


    public VaccinationCenter getCenter() {
        return center;
    }

    public void setCenter(VaccinationCenter center) {
        this.center = center;
    }

    public List<Citizen> getCitizen() {
        return citizen;
    }

    public void setCitizen(List<Citizen> citizen) {
        this.citizen = citizen;
    }
}
