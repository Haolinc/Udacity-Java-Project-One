import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.*;

public class AdminMenu {
    public static void adminMenuOpen(Scanner scanner){
        AdminResource adminResource = new AdminResource();
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("---------------------------");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Add Test Data");
            System.out.println("6. Back to Main Menu");
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1) {
                    Collection<Customer> cus = adminResource.getAllCustomers();
                    for (Customer c: cus){
                        System.out.println(c);
                    }
                } else if (input == 2) {
                    Collection<IRoom> rooms = adminResource.getAllRooms();
                    for (IRoom r: rooms){
                        System.out.println(r);
                    }
                } else if (input == 3) {
                    adminResource.displayAllReservations();
                } else if (input == 4) {
                    List<IRoom> rooms = new ArrayList<>();
                    while (true) {
                        Room r = new Room();
                        System.out.println("Enter room number");
                        r.setRoomNumber(Integer.valueOf(scanner.nextLine()).toString());
                        System.out.println("Enter price per night");
                        r.setPrice(Double.parseDouble(scanner.nextLine()));
                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        int roomInput = Integer.parseInt(scanner.nextLine());
                        if (roomInput == 1) {
                            r.setRoomType(RoomType.SINGLE);
                        }
                        else if (roomInput == 2) {
                            r.setRoomType(RoomType.DOUBLE);
                        }
                        else {
                            System.out.println("Invalid Input");
                            break;
                        }
                        System.out.println("Would you like to add another room y/n");
                        String continueInput = scanner.nextLine();
                        while (true){
                            if (continueInput.equalsIgnoreCase("y")) {
                                rooms.add(r);
                                break;
                            }
                            else if (continueInput.equalsIgnoreCase("n")){
                                rooms.add(r);
                                adminResource.addRoom(rooms);
                                break;
                            }
                            else {
                                System.out.println("Please enter Y (Yes) or N (No)");
                                continueInput = scanner.nextLine();
                            }
                        }
                        if (continueInput.equalsIgnoreCase("n")){
                            break;
                        }

                    }

                } else if (input == 5) {
                    System.out.println("Testing Testing 123");
                } else if (input == 6) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid Input From Admin");
            }
        }
    }
}
