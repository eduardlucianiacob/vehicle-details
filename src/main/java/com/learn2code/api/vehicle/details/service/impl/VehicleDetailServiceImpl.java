package com.learn2code.api.vehicle.details.service.impl;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound {
        return null;
    }

    @Override
    public void deleteVehicleDetailsById(int vehicleId) throws VehicleDetailsNotFound {

    }

    @Override
    public VehicleDetail updateVehicleDetails(int vehicleId, VehicleDetail vehicleDetail) throws VehicleDetailsNotFound {
        return null;
    }

    @Override
    public List<VehicleDetail> fetchFilteredVehiclesDetails(String modelYear, String brand, String model, String trim, double price) {
        if(modelYear != "" && brand!="" && model!=""&& trim!="" && price>0.0){
            return vehicleDetailsDAO.filterVehiclesBasedOnCriteria(modelYear, brand, model, trim, price);
        }


        return null;
    }
}
