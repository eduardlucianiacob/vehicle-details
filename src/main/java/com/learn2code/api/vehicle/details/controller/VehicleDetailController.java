package com.learn2code.api.vehicle.details.controller;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.MandatoryFieldsMissingException;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
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
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicleDetailsById(@PathVariable int vehicleId) throws VehicleDetailsNotFound
    {
        vehicleDetailService.deleteVehicleDetailsById(vehicleId);
        return new ResponseEntity<>("Deleted vehicle details from DB with ID-"+ vehicleId, HttpStatus.OK);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDetail> updateVehicleById(@PathVariable int vehicleId, @RequestBody VehicleDetail vehicleDetail) throws VehicleDetailsNotFound {
        VehicleDetail savedVehicle = vehicleDetailService.updateVehicleDetails(vehicleId, vehicleDetail);
        return ResponseEntity.status(HttpStatus.OK).body(savedVehicle);
    }

}
