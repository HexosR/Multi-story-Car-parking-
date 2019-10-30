import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CarParkAPP {

    private Scanner scan = new Scanner(System.in);
    private Garage garage;
    private ArrayList<Receipt> receipts = new ArrayList<>();
    private String password = "admin";

    private CarParkAPP() {
        garage = new Garage();
    }

    /**
     * Function which calculates cost and change for customer depends on the day of the week and disability card
     * @param receipt
     */
    public void toPay(Receipt receipt) {
        Vehicle vehicle = receipt.getVehicle();
        Calendar startTime = receipt.getStartDateTime();
        AllZones zone = vehicle.getZone();
        System.out.println(startTime.getTime());
        float pay = calculatePayment(startTime, zone);
        if (vehicle.isDisabled()) {
            pay = 2 / pay;
            if (startTime.get(Calendar.DAY_OF_WEEK) == 1) {
                pay = 0;
            }
        }
        float cost;
        System.out.println("You must pay " + pay);
        do {
            System.out.println("Pay now: ");
            cost = scan.nextFloat();
            if (cost < pay) {
                System.out.println("That's not enough, pay more");
            }
        } while (cost < pay);
        float change = cost - pay;
        System.out.println("Change: " + change);
    }

    /**
     * Function which calculates time which vehicle was in garage in correct zone and returns how much customer must pay
     * @param startTime
     * @param zone
     * @return How much to pay
     */
    public float calculatePayment(Calendar startTime, AllZones zone) {
        Calendar endDateTime = getDate();
        System.out.println(endDateTime.getTime());
        long difference = (endDateTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;
        float toPay = zone.howMuchToPay(difference);
        return toPay;
    }

    /**
     * Function created to close the garage for customers who didn't make it in 15 minutes
     * But I don't know where should I implement it so I just created it
     * @param token
     */
    public void leaveCarPark(Token token) {
        if (token.isAvailable()) {
            Calendar currentDateTime = Calendar.getInstance();
            long check = currentDateTime.getTimeInMillis() - token.getTime().getTimeInMillis();
            if (check > 900000) {
                System.out.println("Contact our help service  ");
            } else {
                System.out.println("Good Bye");
            }

        } else {
            System.err.println("Wrong token, call our help service");
        }
    }

    /**
     * Function which checks Array List of the receipts and removes this receipt which customer typed in
     */
    public void CollectCar() {
        System.out.println("Welcome. Enter your receipt number");
        int number = scan.nextInt();
        for (Receipt r : receipts) {
            if (r.getNumber() == number) {
                toPay(r);
                Vehicle vehicle = r.getVehicle();
                if (vehicle.isiNeedAttendant()) {
                    System.out.println("Attendant is collecting your vehicle now");
                    vehicle.getAttendant().setVehicle(null);
                }
                r.getParkingSpot().setOccupied(true);
                receipts.remove(r);
                System.out.println("To leave the Garage you have 15 minutes");
                runUserMenu();
            } else {
                System.err.println("Wrong number! Try again");
                CollectCar();
            }
        }
    }

    private void printUserMenu() {
        System.out.println("1 -  Register Vehicle");
        System.out.println("2 -  Collect Vehicle");
        System.out.println("3 -  Go to admin menu");
        System.out.println("q -  Quit");
    }

    private void printAdminMenu() {
        System.out.println("1 -  Add attendant");
        System.out.println("2 -  Remove attendant");
        System.out.println("3 -  Display garage");
        System.out.println("4 -  Display attendants");
        System.out.println("5 -  Change password");
        System.out.println("6 -  Go to user menu");
        System.out.println("q -  Quit");
    }

    private void displayGarage() {
        System.out.println(garage.toString());
        for (Receipt r : receipts) {
            System.out.println(r.toString());
        }
    }

    /**
     * Function to check if customer can provide a parking card
     * @param vehicle
     */
    public void setDisabled(Vehicle vehicle) {
            System.out.println("Do you have a disability parking card? (Y/N)");
            String disab = scan.nextLine().toUpperCase();
            switch (disab) {
                case "Y":
                    vehicle.setDisabled(true);
                    break;
                case "N":
                    vehicle.setDisabled(false);
                    break;
                default:
                    System.out.println("Try again");
                    setDisabled(vehicle);
            }
        }

    public String getType() {
        System.out.println("What is type of your vehicle");
        System.out.println("1. Standard ");
        System.out.println("2. Higher ");
        System.out.println("3. Longer ");
        System.out.println("4. Coach ");
        System.out.println("5. Motorbike ");
        System.out.println("i. If you don't know which one, check it here ");
        String type = scan.nextLine();
        return type;
    }

    public void setLicensePlate(Vehicle vehicle) {
        System.out.println("Welcome. Enter your car licence plate number: ");
        String licencePlate = scan.nextLine();
        vehicle.setLicensePlate(licencePlate);
    }

    /**
     * function to register new vehicle
     * check a type of vehicle and bind it with a correct zone
     */
    private void RegisterNew() {
        Vehicle vehicle = new Vehicle();
        AllZones zone = null;
        setLicensePlate(vehicle);
        String type = getType();
        setDisabled(vehicle);
        switch (type) {
            case "1":
                vehicle.setType("Standard");
                zone = garage.whichZone(vehicle.getType());
                break;
            case "2":
                vehicle.setType("Higher");
                zone = garage.whichZone(vehicle.getType());
                break;
            case "3":
                vehicle.setType("Longer");
                zone = garage.whichZone(vehicle.getType());
                break;
            case "4":
                vehicle.setType("Coach");
                zone = garage.whichZone(vehicle.getType());
                break;
            case "5":
                vehicle.setType("Motorbike");
                zone = garage.whichZone(vehicle.getType());
                break;
            case "i":
                info();
                break;
            default:
                System.err.println("Try again");
                RegisterNew();
        }
        if (zone == null) {
            runMenu();
        } else {
            vehicle.setZone(zone);
            vehicle.setParkingSpot(zone.findFreeSpot());
            vehicle.getParkingSpot().setOccupied(true);
            iNeedAttendant(vehicle);
        }
    }

    /**
     * Function to ask customer if he needs an Attendant or not
     *
     * @param vehicle
     */
    public void iNeedAttendant(Vehicle vehicle) {
        System.out.println("Require attendant parking? (Y/N)");
        String response = scan.nextLine().toUpperCase();
        switch (response) {
            case "Y":
                boolean check = garage.vehicleToAttendant(vehicle);
                if (check) {
                    vehicle.getZone().chooseSpot(vehicle);
                }
                createReceipt(vehicle);
                break;
            case "N":
                createReceipt(vehicle);
                break;
            default:
                System.out.println("Try again");
                iNeedAttendant(vehicle);
        }
    }

    /**
     * Function which create a new receipt and adds it to an Array List
     *
     * @param vehicle
     */
    public void createReceipt(Vehicle vehicle) {
        Receipt receipt = new Receipt(vehicle);
        receipts.add(receipt);
        Calendar currentDateTime = getDate();
        receipt.setStartDateTime(currentDateTime);
        System.out.println(receipt.toString());
    }

    /**
     * Info about all Zones for those who don't know where to go
     */
    public void info() {
        System.out.println("Zone 1: Outside, standard-sized zone: not in the multi-story building. (CARS and HIGHER VEHICLES)");
        System.out.println("Zone 2: Outside, longer-sized zone: not in the multi-story building.");
        System.out.println("Zone 3: Outside, coach zone.");
        System.out.println("Zone 4: Multi-story building, standard-sized zone.");
        System.out.println("Zone 5: Multi-story building, motorbike zone.");
        getType();
    }

    /**
     * Simple function to get present time
     *
     * @return date
     */
    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        return date;
    }

    public static void main(String args[]) {
        System.out.println("**********HELLO***********");
        CarParkAPP app = new CarParkAPP();
        app.runMenu();
        System.out.println("***********GOODBYE**********");
    }

    /**
     * If you want you can change a password for the admin manu
     * the issue here is that goes back to 'admin' after closing the program
     */
    public void changePass() {
        System.out.println("Old password: ");
        String change = scan.nextLine();
        if (change.equals(password)) {
            System.out.println("New password: ");
            String newPass = scan.nextLine();
            password = newPass;
        } else {
            System.out.println("Wrong password");
        }
    }

    private void runMenu() {
        String response;
        do {
            System.out.println("Do you want to login as admin or as user or quit? \n Choose: Admin or User or Q ");
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "ADMIN":
                    runAdminMenu();
                    break;
                case "USER":
                    runUserMenu();
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    /**
     * Admin menu
     * Attendants array is empty so you need to add an attendant if you want to use it
     * Firstly check the password
     */
    private void runAdminMenu() {
        System.out.println("Password: ");
        String pass = scan.nextLine();
        if (!pass.equals(password)) {
            System.err.println("Wrong password");
            runMenu();
        }
        String response;
        do {
            printAdminMenu();
            System.out.println("What would you like to do:");
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    garage.addAttendant();
                    break;
                case "2":
                    garage.removeAttendant();
                    break;
                case "3":
                    displayGarage();
                    break;
                case "4":
                    garage.displayAttendants();
                    break;
                case "5":
                    changePass();
                    break;
                case "6":
                    runUserMenu();
                case "Q":
                    break;
                default:
                    runAdminMenu();
            }
        } while (!(response.equals("Q")));
    }

    /**
     * Normal user menu
     * Not everything works as should and as I want to
     */
    private void runUserMenu() {
        String response;
        do {
            printUserMenu();
            System.out.println("What would you like to do:");
            response = scan.nextLine().toUpperCase();
            switch (response) {
                case "1":
                    RegisterNew();
                    break;
                case "2":
                    CollectCar();
                    break;
                case "3":
                    runAdminMenu();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }
}
