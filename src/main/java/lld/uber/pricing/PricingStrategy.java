package lld.uber.pricing;

import lld.uber.Location;

public interface PricingStrategy {
    double calculatePrice(Location source, Location destination);
}
