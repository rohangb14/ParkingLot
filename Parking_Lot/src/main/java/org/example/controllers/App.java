package org.example.controllers;

import org.example.dtos.IssueTicketRequest;
import org.example.dtos.IssueTicketResponse;
import org.example.models.*;
import org.example.repositories.GateRepository;
import org.example.repositories.ParkingLotRepository;
import org.example.repositories.TicketRepository;
import org.example.repositories.VehicleRepository;
import org.example.services.TicketService;
import org.example.strategies.SimpleParkingSpotAllotmentStrategy;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        //We need to create a parking lot first

        Operator op = new Operator();
        op.setEmpId(101);
        op.setName("Ravi");

        Gate gate = new Gate(1, GateType.ENTRY, op, GateStatus.WORKING);

        Floor floor1 = new Floor(1);

        for(int i=1;i<=10;i++){
            floor1.getParkingSpots().add(
                    new ParkingSpot(i,VehicleType.FOUR_WHEELER,ParkingSpotStatus.AVAILABLE,floor1));
        }

        ArrayList<Floor> floors = new ArrayList<>();
        floors.add(floor1);
        ArrayList<Gate> gates = new ArrayList<>();
        gates.add(gate);

        ParkingLot parkingLot = new ParkingLot(1l,floors,gates,ParkingLotStatus.ACTIVE);

        GateRepository gateRepository = new GateRepository();
        gateRepository.getGates().put(1l,gate);

        VehicleRepository vehicleRepository = new VehicleRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        parkingLotRepository.getParkingLotMap().put(1l,parkingLot);

        TicketRepository ticketRepository = new TicketRepository();

        TicketService ticketService = new TicketService(gateRepository,vehicleRepository,parkingLotRepository,ticketRepository);

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketRequest issueTicketRequest = new IssueTicketRequest(
                VehicleType.FOUR_WHEELER, "KA-02-Ex-1212", "Prashant", 1l,1l,new SimpleParkingSpotAllotmentStrategy());

        IssueTicketResponse ticketResponse = ticketController.issueTicket(issueTicketRequest);
        System.out.println(issueTicketRequest.getOwner());
        System.out.println(ticketResponse.getResponse()+" "+ticketResponse.getMessage());


    }
}
