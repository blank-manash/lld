package lld.parking;

public interface AllocationStrategy {
    boolean hasParkingSpace(VehicleType vType);
    ParkingSpot getParkingSpot(VehicleType type);
}
