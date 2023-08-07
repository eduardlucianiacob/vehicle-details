package com.learn2code.api.vehicle.details.service;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VehicleDetailServiceTest {

    @MockBean
    private VehicleDetailsDAO vehicleDetailsDAO;

    @Autowired
    private VehicleDetailService vehicleDetailService;

    private VehicleDetail vd;

    @Test
    public void testGetVehicleById() throws VehicleDetailsNotFound {
        VehicleDetail dbVehicleDetail = new VehicleDetail(1, "2022", "Toyota", "Corolla", "L", "", 21000.0, 2500, 4.69, "York, PA", "Clean and efficent car", "T-Auto", "321-111-2323");

        Mockito.when(vehicleDetailsDAO.findById(1)).thenReturn(Optional.of(dbVehicleDetail));

        VehicleDetail savedVehicleDetail = vehicleDetailService.getVehicleById(1);
        assertEquals(1, savedVehicleDetail.getId());
        assertEquals("Toyota", savedVehicleDetail.getBrandName());
    }
}
