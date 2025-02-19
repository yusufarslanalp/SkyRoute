package service;

import com.example.dto.RouteDto;
import com.example.model.Location;
import com.example.model.Transportation;
import com.example.model.TransportationType;
import com.example.repository.LocationRepository;
import com.example.repository.TransportationRepository;
import com.example.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    @Mock
    private TransportationRepository transportationRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private RouteService routeService;

    private Location taksim;
    private Location istanbulAirport;
    private Location londonAirport;
    private Location webleyStadium;
    private Transportation taksimToIstanbulAirport;
    private Transportation istanbulAirportToLondonAirport;
    private Transportation londonAirportToWebleyStadium;

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
        webleyStadium = Location.builder()
                .id(4L)
                .name("Webley Stadium")
                .city("London")
                .locationCode("")
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

        londonAirportToWebleyStadium = Transportation.builder()
                        .id(12L)
                        .from(londonAirport)
                        .to(webleyStadium)
                        .type(TransportationType.BUS)
                        .build();
    }

    @Test
    public void shouldReturnDirectFlight() {
        when(locationRepository.findById(2L)).thenReturn(Optional.of(istanbulAirport));
        when(locationRepository.findById(3L)).thenReturn(Optional.of(londonAirport));

        when(transportationRepository.findByFrom(istanbulAirport))
                .thenReturn(Collections.singletonList(istanbulAirportToLondonAirport));
        when(transportationRepository.findByTo(londonAirport))
                .thenReturn(Collections.singletonList(istanbulAirportToLondonAirport));

        when(transportationRepository.findByFromAndToAndType(istanbulAirport, londonAirport, TransportationType.FLIGHT))
                .thenReturn(Collections.singletonList(istanbulAirportToLondonAirport));

        Transportation directFlight = routeService.getRoutes(istanbulAirport.getId(), londonAirport.getId())
                .get(0).getTransportations().get(0);

        assertEquals(istanbulAirportToLondonAirport, directFlight);
    }

    @Test
    public void shouldReturn2TransportationRoute() {
        when(locationRepository.findById(2L)).thenReturn(Optional.of(istanbulAirport));
        when(locationRepository.findById(4L)).thenReturn(Optional.of(webleyStadium));

        when(transportationRepository.findByFrom(istanbulAirport))
                .thenReturn(Collections.singletonList(istanbulAirportToLondonAirport));
        when(transportationRepository.findByTo(webleyStadium))
                .thenReturn(Collections.singletonList(londonAirportToWebleyStadium));

        RouteDto twoTransportationRoute = routeService.getRoutes(istanbulAirport.getId(), webleyStadium.getId()).get(0);

        assertEquals(istanbulAirportToLondonAirport, twoTransportationRoute.getTransportations().get(0));
        assertEquals(londonAirportToWebleyStadium, twoTransportationRoute.getTransportations().get(1));
    }

    @Test
    public void shouldReturn3TransportationRoute() {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(taksim));
        when(locationRepository.findById(4L)).thenReturn(Optional.of(webleyStadium));

        when(transportationRepository.findByFromAndToAndType(taksim, webleyStadium, TransportationType.FLIGHT))
                .thenReturn(Collections.emptyList());
        when(transportationRepository.findByFrom(taksim))
                .thenReturn(Collections.singletonList(taksimToIstanbulAirport));
        when(transportationRepository.findByTo(webleyStadium))
                .thenReturn(Collections.singletonList(londonAirportToWebleyStadium));
        when(transportationRepository.findByFromAndToAndType(istanbulAirport, londonAirport, TransportationType.FLIGHT))
                .thenReturn(Collections.singletonList(istanbulAirportToLondonAirport));

        RouteDto threeTransportationRoute = routeService.getRoutes(taksim.getId(), webleyStadium.getId()).get(0);

        assertEquals(taksimToIstanbulAirport, threeTransportationRoute.getTransportations().get(0));
        assertEquals(istanbulAirportToLondonAirport, threeTransportationRoute.getTransportations().get(1));
        assertEquals(londonAirportToWebleyStadium, threeTransportationRoute.getTransportations().get(2));
    }
}

