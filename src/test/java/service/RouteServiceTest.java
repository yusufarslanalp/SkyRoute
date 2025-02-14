package service;

import com.example.model.Location;
import com.example.model.Transportation;
import com.example.model.TransportationType;
import com.example.repository.TransportationRepository;
import com.example.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    @Mock
    private TransportationRepository transportationRepository;

    @InjectMocks
    private RouteService routeService;

    private Location location1;
    private Location location2;

    @BeforeEach
    public void setUp() {
        location1 = Location.builder().name("Şirinevler").build();
        location2 = Location.builder().name("Istanbul Hava Alanı").build();
    }

    @Test
    public void testGetRoutes() {

        List<List<Transportation>> routes = routeService.getRoutes(location1, location2);

        assertNotNull(routes);
        assertEquals(1, routes.size());
        assertEquals(3, routes.get(0).size());

        Transportation firstRoute = routes.get(0).get(0);
        assertEquals(1L, firstRoute.getId());
        assertEquals("Şirinevler", firstRoute.getFrom().getName());
        assertEquals("Istanbul Hava Alanı", firstRoute.getTo().getName());
        assertEquals(TransportationType.BUS, firstRoute.getType());

        Transportation secondRoute = routes.get(0).get(1);
        assertEquals(2L, secondRoute.getId());
        assertEquals("Istanbul Hava Alanı", secondRoute.getFrom().getName());
        assertEquals("Esenboğa Hava Alanı", secondRoute.getTo().getName());
        assertEquals(TransportationType.FLIGHT, secondRoute.getType());

        Transportation thirdRoute = routes.get(0).get(2);
        assertEquals(3L, thirdRoute.getId());
        assertEquals("Esenboğa Hava Alanı", thirdRoute.getFrom().getName());
        assertEquals("Kızılay Metdanı", thirdRoute.getTo().getName());
        assertEquals(TransportationType.BUS, thirdRoute.getType());
    }

    @Test
    public void voidTest() {
        Arrays.asList(
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

