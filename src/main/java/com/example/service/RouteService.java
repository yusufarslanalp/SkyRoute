package com.example.service;

import com.example.model.Location;
import com.example.model.Transportation;
import com.example.model.TransportationType;
import com.example.repository.LocationRepository;
import com.example.repository.TransportationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class RouteService {
    private final TransportationRepository transportationRepository;


    public List<List<Transportation>> getRoutes(Location from, Location to){
        //transportationRepository.findByFrom(from);
        return Arrays.asList(
          Arrays.asList(
                  Transportation.builder()
                          .id(1L)
                          .from(Location.builder().name("Şirinevler").build())
                          .to(Location.builder().name("Istanbul Hava Alanı").build())
                          .type(TransportationType.BUS)
                          .build(),
                  Transportation.builder()
                          .id(2L)
                          .from(Location.builder().name("Istanbul Hava Alanı").build())
                          .to(Location.builder().name("Esenboğa Hava Alanı").build())
                          .type(TransportationType.FLIGHT)
                          .build(),
                  Transportation.builder()
                          .id(3L)
                          .from(Location.builder().name("Esenboğa Hava Alanı").build())
                          .to(Location.builder().name("Kızılay Metdanı").build())
                          .type(TransportationType.BUS)
                          .build()
          )
        );
    }

}
