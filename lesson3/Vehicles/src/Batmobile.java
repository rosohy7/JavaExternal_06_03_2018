public class Batmobile extends AmphibiousCar implements IFly {
    @Override
    public String toString() {
        return "Batmobile{" +
                "price=" + price +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public Batmobile(long price, int year, int maxSpeed) {
        this(0,0,price, year, maxSpeed);
    }

    public Batmobile(double longtitude, double latitude, long price, int year, int maxSpeed) {
        super(longtitude, latitude, price, year, maxSpeed);
    }

    @Override
    public int maxFlightSpeed() {
        return maxSpeed*10;
    }

}
