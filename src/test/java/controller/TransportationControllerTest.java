package controller;

import com.example.controller.TransportationController;
import com.example.dto.CreateTransportationDto;
import com.example.dto.UpdateTransportationDto;
import com.example.model.Transportation;
import com.example.model.TransportationType;
import com.example.service.TransportationService;
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
public class TransportationControllerTest {

    @Mock
    private TransportationService transportationService;

    @InjectMocks
    private TransportationController transportationController;

    private Transportation transportation1;
    private Transportation transportation2;
    private CreateTransportationDto createTransportationDto;
    private UpdateTransportationDto updateTransportationDto;

    @BeforeEach
    public void setUp() {
        transportation1 = new Transportation();
        transportation2 = new Transportation();

        createTransportationDto = new CreateTransportationDto();
        createTransportationDto.setFromId(1L);
        createTransportationDto.setToId(2L);
        createTransportationDto.setType(TransportationType.BUS);

        updateTransportationDto = new UpdateTransportationDto();
        updateTransportationDto.setType(TransportationType.SUBWAY);
    }

    @Test
    public void shouldReturnAllTransportations() {
        when(transportationService.getTransportations()).thenReturn(Arrays.asList(transportation1, transportation2));

        List<Transportation> transportations = transportationController.getTransportations();

        assertEquals(2, transportations.size());
        assertTrue(transportations.contains(transportation1));
        assertTrue(transportations.contains(transportation2));
    }

    @Test
    public void shouldCreateTransportation() {
        when(transportationService.saveTransportation(createTransportationDto)).thenReturn(transportation1);

        Transportation createdTransportation = transportationController.createTransportation(createTransportationDto);

        assertNotNull(createdTransportation);
        verify(transportationService, times(1)).saveTransportation(createTransportationDto);
    }

    @Test
    public void shouldUpdateTransportation() {
        when(transportationService.updateTransportation(1L, updateTransportationDto)).thenReturn(transportation1);

        Transportation updatedTransportation = transportationController.updateTransportation(1L, updateTransportationDto);

        assertEquals(transportation1, updatedTransportation);
        verify(transportationService, times(1)).updateTransportation(1L, updateTransportationDto);
    }

    @Test
    public void shouldDeleteTransportation() {
        doNothing().when(transportationService).deleteTransportation(1L);

        transportationController.deleteLocation(1L);

        verify(transportationService, times(1)).deleteTransportation(1L);
    }
}

