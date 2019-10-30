public class Zone1 extends AllZones {

    private float cost = 1;

    public Zone1() {
    }


    @Override
    public float howMuchToPay(long diff) {

        float result = super.howMuchToPay(diff) * cost;
        System.out.println(result);
        return result;
    }

    @Override
    public String getZoneName(){
        return "Zone1";
    }

    @Override
    public String  toString(){
        return "Zone 1 " + super.toString();
    }
}



