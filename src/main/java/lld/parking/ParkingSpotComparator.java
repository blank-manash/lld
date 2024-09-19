package lld.parking;

import java.util.Comparator;

public class ParkingSpotComparator implements Comparator<ParkingSpot> {
    @Override
   public int compare(ParkingSpot a, ParkingSpot b){
        return Integer.compare(a.getDistance(), b.getDistance());
   }
}
