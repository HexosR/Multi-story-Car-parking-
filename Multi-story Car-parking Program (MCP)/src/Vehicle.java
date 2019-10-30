import java.util.Objects;

public class Vehicle {

    private String licensePlate;
    private String type;
    private AllZones zone;
    private boolean disabled;
    private boolean iNeedAttendant;
    private Attendant attendant;
    private ParkingSpot parkingSpot;

    public Vehicle() {

    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isiNeedAttendant() {
        return iNeedAttendant;
    }

    public void setZone(AllZones zone) {
        this.zone = zone;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public AllZones getZone() {
        return zone;
    }

    public String getlicensePlate() {
        return licensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public String toString() {
        return "Vehicle license plate : " + this.licensePlate +
                ", Type: " + this.type;
    }


}
