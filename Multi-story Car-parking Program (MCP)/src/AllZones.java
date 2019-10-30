import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a super class for all Zone classes
 */
public class AllZones {

    private ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
    private int slots;
    private Scanner scan = new Scanner(System.in);

    public AllZones() {

    }

    /**
     * Returns a free spot in the zone
     * if every spot is occupied returns null
     * @return
     */
    public ParkingSpot findFreeSpot() {
        ParkingSpot freeSpot = null;
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (!parkingSpot.isOccupied()) {
                freeSpot = parkingSpot;
            }
        }
        return freeSpot;
    }

    /**
     * Function for attendant to choose in which spot he wants to park the vehicle
     * @param vehicle
     */
    public void chooseSpot(Vehicle vehicle) {
        System.out.println("Provide ID of the spot");
        int ID = scan.nextInt();
        ParkingSpot parkingSpot = getParkingSpot(ID);
        if (parkingSpot.isOccupied()) {
            vehicle.setParkingSpot(parkingSpot);
        } else {
            System.out.println("This spot is taken!");
            chooseSpot(vehicle);
        }
    }

    /**
     * Function that creates new Zone and we can choose how much slots we want in which zone
     * @param slots
     * @param z
     */
    public void newZone(int slots, AllZones z) {
        parkingSpots.clear();
        for (int i = 0; i < (slots); i++) {
            ParkingSpot parkingSlot = new ParkingSpot(z);
            parkingSpots.add(i, parkingSlot);
            parkingSlot.setID("S" + (i + 1));
            this.slots = slots;
        }
    }

    /**
     * Calculates cost for garage depending on the hours that vehicle was in the garage
     * @param diff
     * @return
     */
    public float howMuchToPay(long diff) {
        long ifHour = diff % 3600;
        int h = (int) (diff / 3600);
        float equals;
        if (ifHour != 0) {
            equals = (h + 1);
        } else {
            equals = h;
        }
        System.out.println(equals);
        return equals;
    }

    public String getZoneName() {
        return "Zone";
    }

    public ParkingSpot getParkingSpot(int id) {
        return parkingSpots.get(id - 1);
    }

    @Override
    public String toString() {
        return " " + parkingSpots;
    }
}
