package com.example.controller;

import com.example.model.Location;
import com.example.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping( "location" )
@AllArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations(){
        return locationService.getLocations();
    }


    @PostMapping
    public Location createLocation(@RequestBody @Valid Location location){
        return locationService.saveLocation(location);
    }

    @DeleteMapping("/{locationId}")
    public void deleteLocation(@PathVariable @NotNull Long locationId){
        locationService.deleteLocation(locationId);
    }
}
