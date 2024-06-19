package com.example.CitizenService.repository;

import com.example.CitizenService.entity.Citizen;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitizenRepo extends JpaRepository<Citizen,Integer> {

    public List<Citizen> findByVaccinationCenterId(Integer id);
}
