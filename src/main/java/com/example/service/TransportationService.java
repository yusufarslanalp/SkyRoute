package com.example.service;


import com.example.dto.CreateTransportationDto;
import com.example.dto.UpdateTransportationDto;
import com.example.exception.InvalidRequest;
import com.example.model.Location;
import com.example.model.Transportation;
import com.example.repository.TransportationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class TransportationService {
    private final TransportationRepository transportationRepository;
    private final LocationService locationService;


    public List<Transportation> getTransportations(){
        return transportationRepository.findAll();
    }

    public Transportation saveTransportation(CreateTransportationDto createTransportationDto){
        if (createTransportationDto.getFromId()
                .equals(createTransportationDto.getToId())) {
            throw new InvalidRequest("origin and destination can not be same of a transportation");
        }

        Location from = locationService.findById(createTransportationDto.getFromId());
        Location to = locationService.findById(createTransportationDto.getToId());
        return transportationRepository.save(Transportation.builder()
                .from(from)
                .to(to).type(createTransportationDto.getType())
                .build());
    }

    public Transportation updateTransportation(Long transportationId, UpdateTransportationDto updateTransportationDto){
        Transportation transportation = transportationRepository.findById(transportationId).get();
        transportation.setType(updateTransportationDto.getType());
        transportationRepository.save(transportation);
        return transportation;
    }

    public void deleteTransportation(Long transportationId){
        transportationRepository.deleteById(transportationId);
    }

}
