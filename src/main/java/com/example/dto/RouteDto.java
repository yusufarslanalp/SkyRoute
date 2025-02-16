package com.example.dto;

import com.example.model.Transportation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDto {
    int id;
    int flightIndex;
    List<Transportation> transportations;
}
