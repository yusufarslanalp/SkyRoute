package service;

import com.example.dto.CreateTransportationDto;
import com.example.dto.UpdateTransportationDto;
import com.example.exception.InvalidRequest;
import com.example.model.Location;
import com.example.model.Transportation;
import com.example.model.TransportationType;
import com.example.repository.TransportationRepository;
import com.example.service.LocationService;
import com.example.service.TransportationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransportationServiceTest {

    @Mock
    private TransportationRepository transportationRepository;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private TransportationService transportationService;

    private Location taksim;
    private Location istanbulAirport;
    private Location londonAirport;
    private Transportation taksimToIstanbulAirport;
    private Transportation istanbulAirportToLondonAirport;

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

        taksimToIstanbulAirport = Transportation.builder()
                .id(10L)
                .from(taksim)
                .to(istanbulAirport)
                .type(TransportationType.BUS)
                .build();

        istanbulAirportToLondonAirport = Transportation.builder()
                .id(11L)
                .from(istanbulAirport)
                .to(londonAirport)
                .type(TransportationType.FLIGHT)
                .build();
    }

    @Test
    public void shouldReturnAllTransportations() {
        when(transportationRepository.findAll()).thenReturn(Arrays.asList(taksimToIstanbulAirport, istanbulAirportToLondonAirport));

        List<Transportation> transportations = transportationService.getTransportations();

        assertEquals(2, transportations.size());
        assertTrue(transportations.contains(taksimToIstanbulAirport));
        assertTrue(transportations.contains(istanbulAirportToLondonAirport));
    }

    @Test
    public void shouldSaveTransportationSuccessfully() {
        CreateTransportationDto createTransportationDto = new CreateTransportationDto(1L, 2L, TransportationType.BUS);

        when(locationService.findById(1L)).thenReturn(taksim);
        when(locationService.findById(2L)).thenReturn(istanbulAirport);
        when(transportationRepository.save(any(Transportation.class))).thenReturn(taksimToIstanbulAirport);

        Transportation savedTransportation = transportationService.saveTransportation(createTransportationDto);

        assertNotNull(savedTransportation);
        assertEquals(taksimToIstanbulAirport, savedTransportation);
        verify(transportationRepository, times(1)).save(any(Transportation.class));
    }

    @Test
    public void shouldThrowInvalidRequestWhenOriginAndDestinationAreSame() {
        CreateTransportationDto createTransportationDto = new CreateTransportationDto(1L, 1L, TransportationType.BUS);

        Exception exception = assertThrows(InvalidRequest.class, () -> transportationService.saveTransportation(createTransportationDto));
        assertEquals("origin and destination can not be same of a transportation", exception.getMessage());
    }

    @Test
    public void shouldUpdateTransportationSuccessfully() {
        UpdateTransportationDto updateTransportationDto = new UpdateTransportationDto(TransportationType.FLIGHT);

        when(transportationRepository.findById(10L)).thenReturn(Optional.of(taksimToIstanbulAirport));
        when(transportationRepository.save(any(Transportation.class))).thenReturn(taksimToIstanbulAirport);

        Transportation updatedTransportation = transportationService.updateTransportation(10L, updateTransportationDto);

        assertNotNull(updatedTransportation);
        assertEquals(TransportationType.FLIGHT, updatedTransportation.getType());
        verify(transportationRepository, times(1)).save(any(Transportation.class));
    }

    @Test
    public void shouldDeleteTransportationSuccessfully() {
        transportationService.deleteTransportation(10L);
        verify(transportationRepository).deleteById(10L);
    }
}

