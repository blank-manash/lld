package lld.parking;

public class ParkingSpot {
    private final int distance;
    private final VehicleType vehicleType;
    ParkingSpot(int distance, VehicleType vehicleType) {
        this.distance = distance;
        this.vehicleType = vehicleType;
    }
    public int getDistance() {
        return distance;
    }

    public VehicleType getType() {
        return this.vehicleType;
    }
}
