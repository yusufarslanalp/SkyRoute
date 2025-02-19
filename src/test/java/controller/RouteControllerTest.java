package controller;


import com.example.dto.RouteDto;
import com.example.service.RouteService;
import com.example.controller.RouteController;
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
public class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private RouteDto route1;
    private RouteDto route2;

    @BeforeEach
    public void setUp() {
        route1 = new RouteDto();
        route2 = new RouteDto();
    }

    @Test
    public void shouldReturnAllRoutesForGivenIds() {
        when(routeService.getRoutes(1L, 2L)).thenReturn(Arrays.asList(route1, route2));

        List<RouteDto> routes = routeController.getAllLocations(1L, 2L);

        assertEquals(2, routes.size());
        assertTrue(routes.contains(route1));
        assertTrue(routes.contains(route2));
    }
}
