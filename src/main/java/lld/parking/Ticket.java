package lld.parking;

import java.util.Date;

public class Ticket {
    final private String userInfo;
    final private ParkingSpot spot;
    final private long currentTimeStamp;
    private long endTimeStamp;

    Ticket(String userInfo, ParkingSpot spot) {
        this.spot = spot;
        this.userInfo = userInfo;
        this.currentTimeStamp = System.currentTimeMillis();
    }

    public void setEndTimeStamp(long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }
}
