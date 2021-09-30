package model;

import java.util.Calendar;
import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate, checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString(){
        return customer.toString() + " \nRoom: " + room + ", Check in date: " + checkInDate + ", Check out date: " + checkOutDate + "\n";
    }

    public IRoom getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
}
