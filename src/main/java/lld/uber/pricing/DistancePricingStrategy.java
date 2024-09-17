package lld.uber.pricing;

import lld.uber.Location;

public class DistancePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Location source, Location destination) {
        int distance = Location.calculateDistance(source, destination);
        return Math.random() * distance;
    }
}
