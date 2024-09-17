package lld.uber;

public class Driver extends User {
    Location location;
    boolean isAvailable = true;

    public Driver(String firstName, String lastName) {
        super(firstName, lastName);
        this.location = new Location(3, 2);
    }


    public Location getLocation() {
        return this.location;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
