package org.example.controllers;

import org.example.dtos.IssueTicketRequest;
import org.example.dtos.IssueTicketResponse;
import org.example.exceptions.GateNotFoundException;
import org.example.exceptions.ParkingLotFullException;
import org.example.exceptions.ParkingLotNotFoundException;
import org.example.models.Ticket;
import org.example.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponse issueTicket(IssueTicketRequest request){
        Ticket ticket = null;
        try{
            ticket = ticketService.issueTicket(request);
        } catch (GateNotFoundException e){
            System.out.println("Gate not found!!");
            return new IssueTicketResponse(null,"FAILURE","INVALID GATE");
        } catch(ParkingLotNotFoundException e){
            System.out.println("Parking lot not found");
            return new IssueTicketResponse(null,"FAILURE","PARKING LOT NOT FOUND!!");
        } catch(ParkingLotFullException e){
            System.out.println("Parking lot full");
            return new IssueTicketResponse(null,"failure","ParkingLot FULL");
        }
        return new IssueTicketResponse(ticket,"SUCCESS","Ticket Generated :)");

    }
}
