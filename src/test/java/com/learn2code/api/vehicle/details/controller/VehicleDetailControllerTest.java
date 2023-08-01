package com.learn2code.api.vehicle.details.controller;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class VehicleDetailControllerTest {

    VehicleDetailService vehicleDetailService;

    @Test
    public void testGetAllVehicles() throws VehicleDetailsNotFound {
        List<VehicleDetail> output = Arrays.asList(
                new VehicleDetail(1, "2022", "Toyota", "Corolla", "L", "",21000.0,2500,4.69,"York, PA", "Clean and efficient car","T-Auto","321-11-2323"),
                new VehicleDetail(2, "2022", "Honda", "Civic", "EX", "",23000.0,2500,4.69,"York, PA", "Clean and efficient car","T-Auto","321-111-2323"),
                new VehicleDetail(3, "2021", "Honda", "Camry", "LS", "",24500.0,2300,5.69,"Erie, PA", "Clean and efficient car","D-Auto","211-222-2323")

        );

        Mockito.when(vehicleDetailService.fetchAllVehicleDetails()).then(output);

        mockMvc.perform(MockMvcResultMatchers.get("/api/v1/vehicle-details"))
                .contentType(MediaType.APPLICATION_JSON)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].brandName").value("Honda"));

    }
}
