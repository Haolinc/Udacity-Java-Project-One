package model;

public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    public void setRoomType(RoomType enumeration){
        this.enumeration = enumeration;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString() {
        return "RoomNumber: " + roomNumber + ", Price: " + price + ", RoomType: " + enumeration + "\t";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roomNumber==null)? 0 : roomNumber.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass()!=obj.getClass()) return false;

        Room room = (Room) obj;
        return roomNumber.equals(room.roomNumber);
    }
}
