public class ParkingSpot {

    private String ID;
    private boolean Occupied;
    private AllZones zone;

    public ParkingSpot(AllZones z) {
        zone = z;
        this.ID = ID;
        Occupied = false;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean occupied) {
        Occupied = occupied;
    }

    public AllZones getZone() {
        return zone;
    }

    public void setZone(AllZones zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return
                "ID: " + ID + ' ' +
                        "Occupied: " + Occupied +
                        "\n";
    }

}
