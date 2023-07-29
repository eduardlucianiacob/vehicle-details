package com.learn2code.api.vehicle.details.dao;

import com.learn2code.api.vehicle.details.entities.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsDAO extends JpaRepository<VehicleDetail, Integer> {
}
