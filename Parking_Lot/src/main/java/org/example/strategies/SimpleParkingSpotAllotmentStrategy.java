package org.example.strategies;

import org.example.exceptions.ParkingLotFullException;
import org.example.models.*;

public class SimpleParkingSpotAllotmentStrategy implements ParkingPlaceAllotmentStrategy{
    @Override
    public ParkingSpot getParkingSpot(VehicleType vehicleType, ParkingLot parkingLot) throws ParkingLotFullException {
        /*
         1.Get all the floors of the parking lot
         For each floor, check the slots with vehicletype and status being free
         return it
         */

        for(Floor floor: parkingLot.getFloors()){
            for(ParkingSpot parkingSpot: floor.getParkingSpots()){
                if(parkingSpot.getStatus().equals(ParkingSpotStatus.AVAILABLE)){
                    if(parkingSpot.getVehicleType().equals(vehicleType)){
                        return parkingSpot;
                    }
                }
            }
        }
        //throw an exception saying parking lot is full
        throw new ParkingLotFullException();
    }
}
