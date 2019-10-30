public class Zone2 extends AllZones {

    private float cost = (float) 1.5 ;
    @Override
    public float howMuchToPay(long diff){
        float result = super.howMuchToPay(diff)*cost;
        System.out.println(result);
        return result;
    }
    @Override
    public String getZoneName(){
        return "Zone2";
    }

    @Override
    public String  toString(){
        return "Zone 2 " + super.toString();
    }
}
