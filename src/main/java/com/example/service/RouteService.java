package com.example.service;

import com.example.model.Location;
import com.example.model.Transportation;
import com.example.repository.LocationRepository;
import com.example.repository.TransportationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RouteService {
    private final TransportationRepository transportationRepository;


    public List<List<Transportation>> getRoutes(Location from, Location to){
        transportationRepository.findByFrom(from);
        return null;
    }

}
