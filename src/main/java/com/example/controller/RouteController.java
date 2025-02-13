package com.example.controller;


import com.example.model.Location;
import com.example.model.Transportation;
import com.example.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "route" )
@AllArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public List<List<Transportation>> getAllLocations(Location from, Location to){
        return routeService.getRoutes(from, to);
    }

}
