package lld.uber.assignment;

import lld.uber.Driver;
import lld.uber.Location;
import lld.uber.Ride;

import java.util.Collection;
import java.util.List;

public class MinDistanceAllocation extends AllocationStrategy {
    private final int DISTANCE_THRESHOLD = 10;
    @Override
    public List<Driver> getEligibleDrivers(Collection<Driver> drivers, Ride ride) {
        return drivers
                .parallelStream()
                .filter(Driver::isAvailable)
                .filter(driver -> {
                    int distance = Location.calculateDistance(
                            driver.getLocation(),
                            ride.getSource()
                    );
                    return distance < DISTANCE_THRESHOLD;
                }).toList();
    }
}
