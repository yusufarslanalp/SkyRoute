package service;

import com.example.model.Location;
import com.example.repository.LocationRepository;
import com.example.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

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
        when(locationRepository.findAll()).thenReturn(Arrays.asList(taksim, istanbulAirport, londonAirport));

        List<Location> locations = locationService.getLocations();

        assertEquals(3, locations.size());
        assertTrue(locations.contains(taksim));
        assertTrue(locations.contains(istanbulAirport));
        assertTrue(locations.contains(londonAirport));
    }

    @Test
    public void shouldSaveLocation() {
        when(locationRepository.save(any(Location.class))).thenReturn(taksim);

        Location savedLocation = locationService.saveLocation(taksim);

        assertNotNull(savedLocation);
        assertEquals(taksim, savedLocation);
        verify(locationRepository).save(any(Location.class));
    }

    @Test
    public void shouldDeleteLocation() {
        locationService.deleteLocation(1L);

        verify(locationRepository).deleteById(1L);
    }

    @Test
    public void shouldFindLocationById() {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(taksim));

        Location foundLocation = locationService.findById(1L);

        assertNotNull(foundLocation);
        assertEquals(taksim, foundLocation);
        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldThrowExceptionWhenLocationNotFoundById() {
        when(locationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> locationService.findById(99L));
    }
}
