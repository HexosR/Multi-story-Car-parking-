import java.util.Calendar;

public class Receipt {

    private Calendar startDateTime;
    private int number;
    private Vehicle vehicle;
    private AllZones zone;
    private ParkingSpot parkingSpot;
    private static int addUp;       //number of the receipt starts from 0 and rises

    public Receipt(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.zone = vehicle.getZone();
        this.parkingSpot = vehicle.getParkingSpot();
        this.number = addUp++;
    }

    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Calendar startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getNumber() {
        return number;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public AllZones getZone() {
        return zone;
    }

    public void setZone(AllZones zone) {
        this.zone = zone;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return number == receipt.number;
    }

    @Override
    public String toString() {
        return "Receipt " + "\n" +
                " Number: " + number +
                ", Zone:" + zone +
                ", Vehicle license plate: " + vehicle.getlicensePlate() +
                ", ParkingSpot: " + parkingSpot +
                ", Time: " + startDateTime.getTime();
    }
}
