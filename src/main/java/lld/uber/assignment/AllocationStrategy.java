package lld.uber.assignment;

import lld.uber.Driver;
import lld.uber.Ride;

import java.util.Collection;
import java.util.List;

public abstract class AllocationStrategy {
    public abstract List<Driver> getEligibleDrivers(Collection<Driver> drivers, Ride ride);
}
