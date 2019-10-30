import java.util.ArrayList;

public class Attendant {

    private String ID;
    private String name;
    private Vehicle vehicle;

    public Attendant() {

    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void addToVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isFree() {
        if (this.vehicle == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Attendants: " + "\n" +
                "ID: " + ID +
                ", Name: " + name +
                ", Vehicle: " + vehicle;
    }

}
