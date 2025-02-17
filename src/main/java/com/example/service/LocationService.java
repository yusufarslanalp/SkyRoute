package com.example.service;


import com.example.model.Location;
import com.example.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> getLocations(){
        return locationRepository.findAll();
    }

    public Location saveLocation(Location location){
        return locationRepository.save(location);
    }

    public void deleteLocation(Long locationId){
        locationRepository.deleteById(locationId);
    }

    public Location findById(Long id){
        return locationRepository.findById(id).get();
    }
}
