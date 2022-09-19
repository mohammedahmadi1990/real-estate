package com.realestate.realestatemanagement.repository;


import com.realestate.realestatemanagement.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    List<Estate> findAll();
}