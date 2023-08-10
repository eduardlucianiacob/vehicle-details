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
        List<VehicleDetail> vehicleDetailList = null;
        if(modelYear != "" && brand!="" && model!=""&& trim!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria(modelYear, brand, model, trim, price);
        } else if(brand!="" && model!=""&& trim!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria(brand, model, trim, price);
        } else if(brand!="" && model!=""&& trim!=""){
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria1(brand, model, trim);
        } else if(brand!="" && model!="" && price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria2(brand, model, price);
        } else if(brand!=""&& price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria2(brand,price);
        } else if(brand!="")
        {
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria3(brand);
        } else if(price>0.0){
            vehicleDetailList = vehicleDetailsDAO.filterVehiclesBasedOnCriteria4(price);
        }
       return vehicleDetailList;
    }
}
