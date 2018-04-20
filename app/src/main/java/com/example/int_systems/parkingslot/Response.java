package com.example.int_systems.parkingslot;

public class Response {

    String parkingSlot;
    String Address;
    String numberOfslots;
    String status;
    String parkingID;
    String distance;
    String driverID;

    public Response(String parkingSlot, String address, String numberOfslots, String status, String parkingID,String distance,String driverID) {
        this.parkingSlot = parkingSlot;
        Address = address;
        this.numberOfslots = numberOfslots;
        this.status = status;
        this.parkingID=parkingID;
        this.distance=distance;
        this.driverID =driverID;
    }

    public String getParkingID() {
        return parkingID;
    }

    public void setParkingID(String parkingID) {
        this.parkingID = parkingID;
    }


    public String getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(String parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNumberOfslots() {
        return numberOfslots;
    }

    public void setNumberOfslots(String numberOfslots) {
        this.numberOfslots = numberOfslots;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }
    public String getDriverID() { return driverID; }

    public void setDriverID(String driverID) { this.driverID = driverID; }





}
