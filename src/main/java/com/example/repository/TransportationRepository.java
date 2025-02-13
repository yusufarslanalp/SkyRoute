package com.example.repository;


import com.example.model.Location;
import com.example.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {
    List<Transportation> findByFrom(Location from);

}
