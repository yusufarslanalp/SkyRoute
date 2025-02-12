package com.example.controller;


import com.example.repository.LocationRepository;
import com.example.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "location" )
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping
    public List<Location> getAllLocations(  ){
        return locationRepository.findAll();

    }

}
