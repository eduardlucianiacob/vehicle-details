package com.learn2code.api.vehicle.details.service;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;

public interface VehicleDetailService {
    VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved;
}
