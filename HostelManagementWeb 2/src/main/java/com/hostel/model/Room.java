package com.hostel.model;

public class Room {
    private String roomNo;
    private String hostelType;    
    private String allowedLevel;   
    private int floor;
    private int capacity;
    private String status;         

    
    private int occupied;

    public Room() {}

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public String getHostelType() { return hostelType; }
    public void setHostelType(String hostelType) { this.hostelType = hostelType; }

    public String getAllowedLevel() { return allowedLevel; }
    public void setAllowedLevel(String allowedLevel) { this.allowedLevel = allowedLevel; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getOccupied() { return occupied; }
    public void setOccupied(int occupied) { this.occupied = occupied; }

    public int getAvailableBeds() {
        return Math.max(0, capacity - occupied);
    }
}
