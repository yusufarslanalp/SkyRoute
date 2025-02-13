package com.example.controller;


import com.example.repository.LocationRepository;
import com.example.model.Location;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "location" )
@AllArgsConstructor
public class LocationController {
    private final LocationRepository locationRepository;

    @GetMapping
    public List<Location> getAllLocations(  ){
        return locationRepository.findAll();

    }

}
