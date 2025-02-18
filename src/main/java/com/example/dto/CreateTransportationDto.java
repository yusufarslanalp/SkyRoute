package com.example.dto;

import com.example.model.TransportationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransportationDto {
    @NotNull
    private Long fromId;
    @NotNull
    private Long toId;
    @NotNull
    private TransportationType type;
}