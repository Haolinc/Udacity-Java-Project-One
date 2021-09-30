package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();
    public Customer getCustomer(String customerEmail){
        return customerService.getCustomer(customerEmail);
    }
    public void addRoom(List<IRoom> rooms){  //Since service class method only able to add one room, therefore using extra behavior
        for (IRoom r: rooms)
            reservationService.addRoom(r);
    }
    public Collection<IRoom> getAllRooms(){
        return reservationService.rooms;
    }
    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
