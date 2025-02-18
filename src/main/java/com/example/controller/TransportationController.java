package com.example.controller;

import com.example.dto.CreateTransportationDto;
import com.example.dto.UpdateTransportationDto;
import com.example.model.Transportation;
import com.example.service.TransportationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping( "transportation" )
@AllArgsConstructor
public class TransportationController {
    private final TransportationService transportationService;

    @GetMapping
    public List<Transportation> getTransportations(){
        return transportationService.getTransportations();
    }

    @PostMapping
    public Transportation createTransportation(@Valid @RequestBody CreateTransportationDto createTransportationDto){
        return transportationService.saveTransportation(createTransportationDto);
    }

    @PutMapping("/{transportationId}")
    public Transportation updateTransportation( @PathVariable @NotNull Long transportationId,
            @Valid @RequestBody UpdateTransportationDto updateTransportationDto){
        return transportationService.updateTransportation(transportationId, updateTransportationDto);
    }

    @DeleteMapping("/{transportationId}")
    public void deleteLocation(@PathVariable @NotNull Long transportationId){
        transportationService.deleteTransportation(transportationId);
    }

}
