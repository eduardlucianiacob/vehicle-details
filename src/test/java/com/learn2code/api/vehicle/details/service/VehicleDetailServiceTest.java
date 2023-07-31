package com.learn2code.api.vehicle.details.service;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleDetailServiceTest {

    @MockBean
    private VehicleDetailsDAO vehicleDetailsDAO;

    private VehicleDetail vd;

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @BeforeEach
    void setUp() {
        vd = new VehicleDetail();
        vd.setId(1);
        vd.setModelYear("2022");
        vd.setBrandName("Honda");
        vd.setModelName("Accord");
        vd.setTrimType("LS");
        vd.setBodyType("coupe");
        vd.setMiles(1200);
        vd.setPrice(25236.75);
        vd.setInterestRate(6.75);
        vd.setLocation("Seattle, WA");
        vd.setDescription("Excellent, pretty new, 3 years manufacturer warranty, amazing mileage.");
        vd.setSeller("Amazing Auto");
        vd.setSellerPhone("111-123-1234");
    }

    @Test
    @DisplayName("Test vehicle details saved when passed valid input data")
    void saveVehicleDetails() throws VehicleNotSaved {
        VehicleDetail input = new VehicleDetail();
        input.setModelYear("2022");
        input.setBrandName("Honda");
        input.setModelName("Accord");
        input.setTrimType("LS");
        input.setBodyType("coupe");
        input.setMiles(1200);
        input.setPrice(25236.75);
        input.setInterestRate(6.75);
        input.setLocation("Seattle, WA");
        input.setDescription("Excellent, pretty new, 3 years manufacturer warranty, amazing mileage.");
        input.setSeller("Amazing Auto");
        input.setSellerPhone("111-123-1234");

        Mockito.when(vehicleDetailsDAO.save(input)).thenReturn(vd);
        VehicleDetail output = vehicleDetailService.saveVehicleDetails(input);

        assertEquals(output.getId(), vd.getId());
        assertEquals(output.getBrandName(), vd.getBrandName());
        assertEquals(output.getSeller(), vd.getSeller());
    }
}