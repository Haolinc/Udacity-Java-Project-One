import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    public static void main(String[] args) {
        mainMenuOpen();
    }

    public static void mainMenuOpen(){
        HotelResource hotelResource = new HotelResource();
        try(Scanner scanner = new Scanner(System.in)){
            while (true) {
                try {
                    System.out.println("\nWelcome to the Hotel Reservation Application");
                    System.out.println("---------------------------");
                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an Account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    System.out.println("---------------------------");
                    System.out.println("Please select a number for the menu option");
                    int input = Integer.parseInt(scanner.nextLine());
                    switch (input) {
                        case 1 -> {
                            System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                            Date checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
                            System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/21/2020");
                            Date checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
                            if (checkOutDate.before(checkInDate)){        //Checking dates
                                throw new InvalidParameterException();
                            }
                            Collection<IRoom> rooms= new ArrayList<>(hotelResource.findARoom(checkInDate, checkOutDate));
                            if (rooms.isEmpty()){
                                System.out.println("There is no available room within the range right now, searching for recommend room after 7 days of check-in and check-out.");
                                //Add 7 days to checkInDate
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(checkInDate);
                                calendar.add(Calendar.DAY_OF_MONTH, 7);
                                checkInDate = calendar.getTime();
                                System.out.println("The new CheckIn date is: " + checkInDate);
                                //Add 7 days to checkOutDate
                                calendar.setTime(checkOutDate);
                                calendar.add(Calendar.DAY_OF_MONTH, 7);
                                checkOutDate = calendar.getTime();
                                System.out.println("The new CheckOut date is: " + checkOutDate);
                                rooms= new ArrayList<>(hotelResource.findARoom(checkInDate, checkOutDate));
                                if (rooms.isEmpty()) {
                                    System.out.println("No available room");
                                    break;
                                }

                            }
                            System.out.println(rooms);
                            System.out.println("Would you like to book a room? y/n");
                            String bookingInput = scanner.nextLine();

                            //Nested loop to ensure user input correct string
                            if (bookingInput.equalsIgnoreCase("n"))
                                break;
                            else if (bookingInput.equalsIgnoreCase("y")) {
                                System.out.println("Do you have an account with us? y/n");
                                bookingInput = scanner.nextLine();
                                if (bookingInput.equalsIgnoreCase("n")) {
                                    System.out.println("Please create an account in main menu first");
                                }
                                else if (bookingInput.equalsIgnoreCase("y")) {
                                    System.out.println("Enter Email format: name@domain.com");
                                    bookingInput = scanner.nextLine();
                                    Customer currentCustomer = hotelResource.getCustomer(bookingInput);
                                    if (currentCustomer == null)
                                        System.out.println("Account not found");
                                    else {
                                        System.out.println("What room number would you like to reserve");
                                        bookingInput = scanner.nextLine();
                                        IRoom room = hotelResource.getRoom(bookingInput);
                                        if (room == null)
                                            System.out.println("Room not found");
                                        else {
                                            Reservation reservation = hotelResource.bookARoom(currentCustomer.getEmail(), room, checkInDate, checkOutDate);
                                            System.out.println(reservation);
                                        }
                                    }
                                }
                                else
                                    System.out.println("Invalid Input");
                            }
                            else
                                System.out.println("Invalid Input");
                        }
                        case 2 -> {
                            System.out.println("Please enter your email");
                            String email = scanner.nextLine();
                            Collection<Reservation> reservations = new ArrayList<>();
                            reservations = hotelResource.getCustomersReservations(email);
                            if (reservations.isEmpty()) {
                                System.out.println("No reservation has been found");
                                continue;
                            }
                            for (Reservation r : reservations) {
                                System.out.println(r);
                            }
                        }
                        case 3 -> {
                            System.out.println("Enter Email format: name@domain.com");
                            String email = scanner.nextLine();
                            System.out.println("First Name");
                            String firstName = scanner.nextLine();
                            System.out.println("Last Name");
                            String lastName = scanner.nextLine();
                            hotelResource.createACustomer(email, firstName, lastName);
                        }
                        case 4 -> AdminMenu.adminMenuOpen(scanner);
                        case 5 -> System.out.print("");
                        default -> System.out.println("Please select from 1 to 5");
                    }
                    if (input == 5)
                        break;
                } catch (ParseException | NumberFormatException ex) {
                    System.out.println("Invalid Input");
                } catch (InvalidParameterException ex) {
                    System.out.println("Invalid Date");
                }
            }
        }
    }
}
