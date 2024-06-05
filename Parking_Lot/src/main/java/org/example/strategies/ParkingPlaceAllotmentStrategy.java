package org.example.strategies;

import org.example.exceptions.ParkingLotFullException;
import org.example.models.ParkingLot;
import org.example.models.ParkingSpot;
import org.example.models.VehicleType;

public interface ParkingPlaceAllotmentStrategy {
    ParkingSpot getParkingSpot(VehicleType vehicleType, ParkingLot parkingLot) throws ParkingLotFullException;
}
