public class Zone5 extends AllZones {
    private float cost = (float) 0.5;

    @Override
    public float howMuchToPay(long diff) {
        float result = super.howMuchToPay(diff) * cost;
        System.out.println(result);
        return result;
    }

    @Override
    public String getZoneName() {
        return "Zone5";
    }

    @Override
    public String toString() {
        return "Zone 5 " + super.toString();
    }
}
