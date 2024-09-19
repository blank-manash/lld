package lld.parking;

import java.util.EnumMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinDistanceAllocation implements AllocationStrategy {
    EnumMap<VehicleType, Queue<ParkingSpot>> spots;

    public MinDistanceAllocation() {
        this.spots = new EnumMap<>(VehicleType.class);
        for(VehicleType vehicleType : VehicleType.values()) {
            this.spots.put(vehicleType, new PriorityQueue<>(new ParkingSpotComparator()));
        }
    }

    public void addSpot(ParkingSpot spot) {
        this.spots.get(spot.getType()).add(spot);
    }

    public boolean hasParkingSpace(VehicleType vType) {
        return !this.spots.get(vType).isEmpty();
    }

    @Override
    public synchronized ParkingSpot getParkingSpot(VehicleType type) {
        if (this.hasParkingSpace(type)) {
            return this.spots.get(type).poll();
        }
        return null;
    }
}
