package lld.uber;


import lld.uber.assignment.AllocationStrategy;
import lld.uber.assignment.MinDistanceAllocation;
import lld.uber.pricing.DistancePricingStrategy;
import lld.uber.pricing.PricingStrategy;

import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Platform {
    private static Platform instance = null;
    private final AllocationStrategy allocationStrategy;
    private final PricingStrategy pricingStrategy;
    private final Map<String, Driver> availableDrivers = new ConcurrentHashMap<>();

    private Platform() {
        this.allocationStrategy = new MinDistanceAllocation();
        this.pricingStrategy = new DistancePricingStrategy();
    }

    public static synchronized Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public Ride requestRide(Passenger passenger, Location start, Location end) {
        double price = pricingStrategy.calculatePrice(start, end);
        Ride ride = new Ride(start, end, passenger);
        ride.setPrice(price);
        notifyDrivers(ride);
        return ride;
    }

    public void completeRide(Ride ride) {
        ride.setStatus(RideStatus.COMPLETED);
        Driver driver = ride.getAssignedDriver();
        this.availableDrivers.put(driver.getUserId(), driver);
        notifyUser(ride);
    }

    public void acceptRide(Driver driver, Ride ride) {
        if (availableDrivers.containsKey(driver.getUserId())) {
            ride.setAssignedDriver(driver);
            availableDrivers.remove(driver.getUserId());
            ride.setStatus(RideStatus.ACCEPTED);
            notifyUser(ride);
        }
    }

    private void notifyUser(Ride ride) {
        EnumMap<RideStatus, String> notificationMessage = new EnumMap<>(RideStatus.class);
        notificationMessage.put(RideStatus.ACCEPTED, "Ride Accepted");
        notificationMessage.put(RideStatus.CANCELLED, "Ride Cancelled");
        notificationMessage.put(RideStatus.COMPLETED, "Ride Completed");

        if (notificationMessage.containsKey(ride.getStatus())) {
            System.out.println(notificationMessage.get(ride.getStatus()));
        }
    }


    public void notifyDrivers(Ride ride) {
        this.allocationStrategy.getEligibleDrivers(
                this.availableDrivers.values(),
                ride
        ).forEach(
                driver -> {
                    System.out.println("Driver " + driver.getUserId() + " is offered " + ride.getId());
                }
        );
    }

    public void cancel(Ride ride) {
        if (ride.getStatus() == RideStatus.WAITING || ride.getStatus() == RideStatus.ACCEPTED) {
            ride.setStatus(RideStatus.CANCELLED);
            if (ride.hasAssignedDriver()) {
                Driver driver = ride.getAssignedDriver();
                this.availableDrivers.put(driver.getUserId(), driver);
            }
        }
    }
}
