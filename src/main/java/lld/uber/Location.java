package lld.uber;

public record Location(int longitude, int latitude) {
    public static int calculateDistance(Location location, Location location1) {
        return Math.abs(location.longitude() - location1.longitude())
                + Math.abs(location.latitude() - location1.latitude());
    }
};
