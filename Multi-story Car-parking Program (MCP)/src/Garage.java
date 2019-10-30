import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Garage {

    private Zone1 zone1;
    private Zone2 zone2;
    private Zone3 zone3;
    private Zone4 zone4;
    private Zone5 zone5;
    private ArrayList<Attendant> attendants = new ArrayList<>();
    private ArrayList<Attendant> freeAttendants = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    /**
     * Create all zones and choose amount of slots in them
     */
    public Garage() {
        zone1 = new Zone1();
        zone1.newZone(5, zone1);
        zone2 = new Zone2();
        zone2.newZone(5, zone2);
        zone3 = new Zone3();
        zone3.newZone(5, zone3);
        zone4 = new Zone4();
        zone4.newZone(5, zone4);
        zone5 = new Zone5();
        zone5.newZone(5, zone5);
    }

    /**
     * Takes type of the vehicle and returns correct zone
     * also checks if each zone is full
     * @param whichType
     * @return
     */
    public AllZones whichZone(String whichType) {
        AllZones zone = null;
        switch (whichType.toUpperCase()) {
            case "STANDARD":
                zone = zone4;
                if (zone.findFreeSpot() == null) {
                    zone = zone1;
                } else if (ifFull(zone)) {
                    zone = null;
                }
                break;
            case "LONGER":
                zone = zone2;
                if (ifFull(zone)) {
                    zone = null;
                }
                break;
            case "HIGHER":
                zone = zone1;
                if (ifFull(zone)) {
                    zone = null;
                }
                break;
            case "COACH":
                zone = zone3;
                if (ifFull(zone)) {
                    zone = null;
                }
                break;
            case "MOTORBIKE":
                zone = zone5;
                if (ifFull(zone)) {
                    zone = null;
                }
        }
        if (zone == null) {
            System.out.println("Sorry there is no space for you");
        }
        return zone;
    }

    public boolean ifFull(AllZones zone) {
        if (zone.findFreeSpot() == null) {
            System.out.println("Sorry there is no space for you");
            return true;
        }
        return false;
    }

    public void displayAttendants() {
        for (Attendant a : attendants) {
            System.out.println(a.toString());
        }
    }

    /**
     * Creates a new attendant and adds it to the array list
     */
    public void addAttendant() {
        int a = 1;
        a++;
        Attendant attendant = new Attendant();
        System.out.println("Enter name of the new attendant: ");
        String name = scan.nextLine();
        attendant.setID("A" + a);
        attendant.setName(name);
        attendants.add(attendant);
    }

    /**
     * Takes an integer and removes an attendant from the list
     */
    public void removeAttendant() {
        displayAttendants();
        System.out.println("Which attendant do you want to remove? (Provide a number");
        int index = scan.nextInt();
        attendants.remove(index - 1);
    }

    /**
     * Firstly moves all free attendants to the new array list so this busy one will stay in another list
     * Secondly checks if here is a free attendant at all to park a vehicle
     * if there is, picks a random attendant and assign it to the vehicle
     * @param vehicle
     * @return
     */
    public boolean vehicleToAttendant(Vehicle vehicle) {
        for (Attendant a : attendants) {
            if (a.isFree()) {
                freeAttendants.add(a);
            }
        }
        if (freeAttendants.size() >= 1) {
            int size = freeAttendants.size();
            Random random = new Random();
            int index = random.nextInt(size);
            Attendant attendant = freeAttendants.get(index);
            System.out.println("Attendant " + attendant.getID() + " " + attendant.getName() + " will park your car.");
            attendant.addToVehicle(vehicle);
            vehicle.setAttendant(attendant);
            return true;
        } else {
            System.out.println("Sorry all attendants are busy right now and you need to park your vehicle by yourself");
            return false;
        }

    }

    @Override
    public String toString() {
        return "Garage " + "\n" +
                "Zone1: " + "\n" + zone1 +
                "Zone2: " + "\n" + zone2 +
                "Zone3: " + "\n" + zone3 +
                "Zone4: " + "\n" + zone4 +
                "Zone5: " + "\n" + zone5;
    }
}