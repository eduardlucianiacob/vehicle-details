package com.learn2code.api.vehicle.details.service.impl;

import com.learn2code.api.vehicle.details.dao.VehicleDetailsDAO;
import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import com.learn2code.api.vehicle.details.errors.VehicleDetailsNotFound;
import com.learn2code.api.vehicle.details.errors.VehicleNotSaved;
import com.learn2code.api.vehicle.details.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        Optional<VehicleDetail> optionalVehicleDetail = vehicleDetailsDAO.findById(vehicleId);
        if(!optionalVehicleDetail.isPresent()) {
            throw new VehicleDetailsNotFound("No vehicle found in DB for ID-" + vehicleId);
        }

        VehicleDetail dbVehicle = optionalVehicleDetail.get();
        if(vehicleDetail.getModelYear() != "" && Objects.nonNull(vehicleDetail.getModelYear())){
            dbVehicle.setModelYear(vehicleDetail.getModelYear());
        }

        if(vehicleDetail.getBrandName() != "" && Objects.nonNull(vehicleDetail.getBrandName())){
            dbVehicle.setBrandName(vehicleDetail.getBrandName());
        }

        if(vehicleDetail.getModelName() != "" && Objects.nonNull(vehicleDetail.getModelName())){
            dbVehicle.setModelName(vehicleDetail.getModelName());
        }

        if(vehicleDetail.getPrice() != 0.0 && Objects.nonNull(vehicleDetail.getPrice())){
            dbVehicle.setPrice(vehicleDetail.getPrice());
        }

        if(vehicleDetail.getMiles() != 0 && Objects.nonNull(vehicleDetail.getMiles())){
            dbVehicle.setMiles(vehicleDetail.getMiles());
        }

        if(vehicleDetail.getInterestRate() != 0.0 && Objects.nonNull(vehicleDetail.getInterestRate())){
            dbVehicle.setInterestRate(vehicleDetail.getInterestRate());
        }

        if(vehicleDetail.getSeller() != "" && Objects.nonNull(vehicleDetail.getSeller())){
            dbVehicle.setSeller(vehicleDetail.getSeller());
        }

        if(vehicleDetail.getSellerPhone() != "" && Objects.nonNull(vehicleDetail.getSellerPhone())){
            dbVehicle.setSellerPhone(vehicleDetail.getSellerPhone());
        }

        dbVehicle.setTrimType(vehicleDetail.getTrimType());
        dbVehicle.setBodyType(vehicleDetail.getBodyType());
        dbVehicle.setLocation(vehicleDetail.getLocation());
        dbVehicle.setDescription(vehicleDetail.getDescription());

        return vehicleDetailsDAO.save(dbVehicle);
    }
}
