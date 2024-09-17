package lld.uber;

import java.util.Optional;
import java.util.UUID;

public class Ride {
    private final String id;
    private final Location source;
    private final Location destination;
    private final Passenger passenger;
    private Double price;
    private RideStatus status;
    private Driver assignedDriver;

    Ride(Location source, Location destination, Passenger passenger) {
        this.source = source;
        this.destination = destination;
        this.passenger = passenger;
        this.id = String.valueOf(UUID.randomUUID());
        this.status = RideStatus.WAITING;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public Driver getAssignedDriver() {
        return assignedDriver;
    }

    public boolean hasAssignedDriver() {
        return this.assignedDriver != null;
    }

    public void setAssignedDriver(Driver assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public Double getPrice() {
        return price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public RideStatus getStatus() {
        return status;
    }

    public String getId() {
        return this.id;
    }
}
