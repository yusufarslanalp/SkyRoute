package com.example.controller;


import com.example.model.Transportation;
import com.example.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "route" )
@AllArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<List<Transportation>> getAllLocations(@RequestParam Long fromId, @RequestParam Long toId){
        return routeService.getRoutes(fromId, toId);
    }

}
