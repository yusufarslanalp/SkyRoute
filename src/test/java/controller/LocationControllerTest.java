package controller;

import com.example.model.Location;
import com.example.service.LocationService;
import com.example.controller.LocationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    private Location taksim;
    private Location istanbulAirport;
    private Location londonAirport;

    @BeforeEach
    public void setUp() {
        taksim = Location.builder()
                .id(1L)
                .name("Taksim Square")
                .city("İstanbul")
                .locationCode("")
                .build();
        istanbulAirport = Location.builder()
                .id(2L)
                .name("İstanbul Airport")
                .city("İstanbul")
                .locationCode("IST")
                .build();
        londonAirport = Location.builder()
                .id(3L)
                .name("London Heathrow Airport")
                .city("London")
                .locationCode("LHR")
                .build();
    }

    @Test
    public void shouldReturnAllLocations() {
        when(locationService.getLocations()).thenReturn(Arrays.asList(taksim, istanbulAirport, londonAirport));

        List<Location> locations = locationController.getAllLocations();

        assertEquals(3, locations.size());
        assertTrue(locations.contains(taksim));
        assertTrue(locations.contains(istanbulAirport));
        assertTrue(locations.contains(londonAirport));
    }

    @Test
    public void shouldCreateLocation() {
        when(locationService.saveLocation(any(Location.class))).thenReturn(taksim);

        Location createdLocation = locationController.createLocation(taksim);

        assertNotNull(createdLocation);
        assertEquals(taksim, createdLocation);
        verify(locationService, times(1)).saveLocation(any(Location.class));
    }

    @Test
    public void shouldUpdateLocation() {
        when(locationService.saveLocation(any(Location.class))).thenReturn(taksim);

        Location updatedLocation = locationController.updateLocation(taksim);

        assertNotNull(updatedLocation);
        assertEquals(taksim, updatedLocation);
        verify(locationService, times(1)).saveLocation(any(Location.class));
    }

    @Test
    public void shouldDeleteLocation() {
        doNothing().when(locationService).deleteLocation(1L);

        locationController.deleteLocation(1L);

        verify(locationService, times(1)).deleteLocation(1L);
    }
}
