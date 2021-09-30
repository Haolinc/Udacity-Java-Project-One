package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    //Singleton creation
    private static ReservationService RESERVATIONINSTANCE;
    private ReservationService(){}
    public static ReservationService getInstance(){
        if (RESERVATIONINSTANCE == null)
            RESERVATIONINSTANCE = new ReservationService();
        return RESERVATIONINSTANCE;
    }

    //Collection variables
    public Collection<Reservation> reservations = new ArrayList<>();
    public Set<IRoom> rooms = new HashSet<>();

    //Implementing methods
    public void addRoom(IRoom room) {
        rooms.add(room);
    }
    public IRoom getARoom(String roomId) {
        for (IRoom r: rooms){
            if (r.getRoomNumber().equals(roomId))
                return r;
        }
        return null;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }


    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return searchForRecommendedRoom(checkInDate, checkOutDate);
    }

    //Check for time overlapped room first
    //then delete those rooms from set and return the available rooms.
    Collection<IRoom> searchForRecommendedRoom(Date newCheckInDate, Date newCheckOutDate) {
        Collection<IRoom> notAvailableRooms= new HashSet<>();
        Collection<IRoom> availableRooms = new ArrayList<>(rooms);
        for (Reservation r: reservations){
            if (newCheckInDate.getTime() < r.getCheckInDate().getTime()) {
                if (newCheckOutDate.getTime() > r.getCheckInDate().getTime()){
                    notAvailableRooms.add(r.getRoom());
                }
            }
            else if (newCheckInDate.getTime() > r.getCheckInDate().getTime()) {
                if (newCheckInDate.getTime()<r.getCheckOutDate().getTime()){
                    notAvailableRooms.add(r.getRoom());
                }
            }
            else if (newCheckInDate.getTime() == r.getCheckInDate().getTime())
                notAvailableRooms.add(r.getRoom());
        }
        availableRooms.removeAll(notAvailableRooms);
        return availableRooms;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        for (Reservation r: reservations){
            if (r.getCustomer().equals(customer)){
                customerReservations.add(r);
            }
        }
        return customerReservations;
    }
    public void printAllReservation(){
        if (!reservations.isEmpty())
            for (Reservation r: reservations){
                System.out.println(r);
            }
    }


}
