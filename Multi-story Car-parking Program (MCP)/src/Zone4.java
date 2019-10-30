public class Zone4 extends AllZones {
    private float cost = 1;

    @Override
    public float howMuchToPay(long diff) {
        float result = super.howMuchToPay(diff) * cost;
        System.out.println(result);
        return result;
    }

    @Override
    public String getZoneName() {
        return "Zone4";
    }

    @Override
    public String toString() {
        return "Zone 4 " + super.toString();
    }
}
