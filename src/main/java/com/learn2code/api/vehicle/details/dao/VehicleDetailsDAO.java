package com.learn2code.api.vehicle.details.dao;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleDetailsDAO extends JpaRepository<VehicleDetail, Integer> {

    @Query(value="SELECT * FROM vehicle_details WHERE model_year=?1 AND brand_name=?2 AND model_name=?3 AND trim_type=?4 AND price<=?5", nativeQuery = true)
    List<VehicleDetail> filterVehiclesBasedOnCriteria(String modelYear, String brand, String model, String trim, double price);
}
