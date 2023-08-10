package com.learn2code.api.vehicle.details.controller;

import com.learn2code.api.vehicle.details.dto.VehicleDetailsDTO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.MandatoryFieldsMissingException;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailController {

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @PostMapping
    public ResponseEntity<VehicleDetail> saveVehicleDetails(@Valid @RequestBody VehicleDetail vehicleDetail, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            String allErrors = "";
            for(ObjectError err: errorList){
                allErrors += err.getDefaultMessage() + ",";
            }

            throw new MandatoryFieldsMissingException(allErrors);
        }
        VehicleDetail dbVehicle = vehicleDetailService.saveVehicleDetails(vehicleDetail);
        return new ResponseEntity<>(dbVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public VehicleDetailsDTO getVehiclesByCriteria(@RequestParam String modelYear, @RequestParam String brand, @RequestParam String model, @RequestParam String trim, @RequestParam String price){

        if(price == "" || price==null){
            price="0.0";
        }
        List<VehicleDetail> filteredVehicles=vehicleDetailService.fetchFilteredVehiclesDetails(modelYear, brand, model, trim, Double.parseDouble(price));
        return new VehicleDetailsDTO(filteredVehicles);
    }


}
