package com.example.service;

import com.example.model.Location;
import com.example.model.Transportation;
import com.example.repository.LocationRepository;
import com.example.repository.TransportationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class RouteService {
    private final TransportationRepository transportationRepository;
    private final LocationRepository locationRepository;

    public List<List<Transportation>> getRoutes(Long fromId, Long toId){
        Location from = locationRepository.findById(fromId).get();
        Location to = locationRepository.findById(toId).get();

        List<Transportation> firstTransports = transportationRepository.findByFrom(from);
        List<Transportation> lastTransports = transportationRepository.findByTo(to);

        List<List<Transportation>> routes = new ArrayList<>();
        for(Transportation firsTransport : firstTransports){
            for(Transportation lastTransport : lastTransports){
                List<Transportation> connector = transportationRepository
                        .findByFromAndTo(firsTransport.getTo(), lastTransport.getFrom());
                if(connector.size() > 0){
                    routes.add(
                            Arrays.asList(
                                    firsTransport,
                                    connector.get(0),
                                    lastTransport
                            )
                    );
                }
            }
        }

        return routes;
    }

}
