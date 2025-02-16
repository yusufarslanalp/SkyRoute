package com.example.dto;

import com.example.model.Transportation;

import java.util.ArrayList;
import java.util.List;

public class RouteDtoConverter {

    public static List<RouteDto> convert(List<List<Transportation>> routes){
        int id = 0;
        List<RouteDto> routeDtoList = new ArrayList<>();
        for(List<Transportation> route : routes){
            routeDtoList.add(
                    RouteDto.builder()
                            .id(id)
                            .flightIndex(getFlightIndex(route))
                            .transportations(route)
                            .build()
            );
            id++;
        }
        return routeDtoList;
    }

    private static int getFlightIndex(List<Transportation> route){
        if(route.size() == 1){
            return 0;
        }
        if(route.size() == 3){
            return 1;
        }
        if (route.size() == 2){
            if(route.get(0).isFlight()){
                return 0;
            }
            return 1;
        }
        return -1;
    }
}
