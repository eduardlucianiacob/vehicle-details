package com.learn2code.api.vehicle.details.service.impl;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {

    @Autowired
    private VehicleDetailsDAO vehicleDetailsDAO;

    @Override
    public VehicleDetail saveVehicleDetails(VehicleDetail vehicleDetail) throws VehicleNotSaved {
        VehicleDetail dbVehicle = null;
        try {
            dbVehicle = vehicleDetailsDAO.save(vehicleDetail);
        } catch(Exception e){
            throw new VehicleNotSaved("Unable to save vehicle in DB. Got Error" + e.getMessage());
        }
        return vehicleDetailsDAO.save(vehicleDetail);
    }

    @Override
    public List<VehicleDetail> fetchAllVehicleDetails() throws VehicleDetailsNotFound {
        List<VehicleDetail> dbVehicles = vehicleDetailsDAO.findAll();

        if(dbVehicles.size() == 0){
            throw new VehicleDetailsNotFound("No vehicle details found in Database!");
        }

        return dbVehicles;
    }

    @Override
    public VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsDAO.findById(vehicleId);

        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle details found in database for vehicle ID-"+ vehicleId);
        }
        return optionalVehicleDetail.get();
    }

    @Override
    public void deleteVehicleDetailsById(int vehicleId) throws VehicleDetailsNotFound {
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsDAO.findById(vehicleId);

        if(!optionalVehicleDetail.isPresent()){
            throw new VehicleDetailsNotFound("No vehicle details found in database for vehicle ID-"+ vehicleId);
        }

        vehicleDetailsDAO.deleteById(vehicleId);
    }
}
