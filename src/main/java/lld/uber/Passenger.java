package lld.uber;

public class Passenger extends User {
    Platform uber;
    Payment paymentMethod;
    Ride ride = null;

    Passenger(String firstName, String lastName, Payment payment) {
        super(firstName, lastName);
        this.paymentMethod = payment;
        uber = Platform.getInstance();
    }

    void requestRide() {
        this.ride = uber.requestRide(this, new Location(0, 0), new Location(1, 1));
    }

    void cancelRide() {
        uber.cancel(ride);
        this.ride = null;
    }

    void dropOff() {
        this.ride = null;
    }

};