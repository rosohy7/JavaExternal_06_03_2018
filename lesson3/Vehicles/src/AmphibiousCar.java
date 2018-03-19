public class AmphibiousCar extends Car implements ISwim {

    @Override
    public int maxSwimSpeed() {
        return maxSpeed/2;
    }

    @Override
    public String toString() {
        return "AmphibiousCar{" +
                "price=" + price +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public AmphibiousCar(long price, int year, int maxSpeed) {
        this(0,0,price, year, maxSpeed);
    }

    public AmphibiousCar(double longtitude, double latitude, long price, int year, int maxSpeed) {
        super(longtitude, latitude, price, year, maxSpeed);
    }
}
