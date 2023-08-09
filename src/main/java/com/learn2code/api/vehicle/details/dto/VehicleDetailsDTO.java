package com.learn2code.api.vehicle.details.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailsDTO {
    List<VehicleDetailsDTO> vehicleDetailList;
}
