package lld.parking;

public class EntryTerminal {
    private final AllocationStrategy allocationStrategy;
    public EntryTerminal(AllocationStrategy strategy) {
        this.allocationStrategy = strategy;
    }
    public synchronized Ticket getTicket(String userInfo, VehicleType vehicleType) {
        if (this.allocationStrategy.hasParkingSpace(vehicleType)) {
            ParkingSpot spot = this.allocationStrategy.getParkingSpot(vehicleType);
            return new Ticket(userInfo, spot);
        }
        System.out.println("No Spot Available");
        return null;
    }
}
