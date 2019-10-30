public class Zone3 extends AllZones {
    private float cost = 2;

    @Override
    public float howMuchToPay(long diff) {
        float result = super.howMuchToPay(diff) * cost;
        System.out.println(result);
        return result;
    }

    @Override
    public String getZoneName() {
        return "Zone3";
    }

    @Override
    public String toString() {
        return "Zone 3 " + super.toString();
    }
}
