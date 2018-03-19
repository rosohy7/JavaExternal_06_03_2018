public class Plane extends Vehicle implements IFly {
    double elevation;
    int maxPassengers;

    @Override
    public String toString() {
        return "Plane{" +
                "maxPassengers=" + maxPassengers +
                ", price=" + price +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public Plane(long price, int year, int maxSpeed, int maxPassengers) {
        this(0, 0, 0, price, year, maxSpeed, maxPassengers);
    }

    public Plane(double longtitude, double latitude, double elevation, long price, int year, int maxSpeed, int maxPassengers) {
        super(longtitude, latitude, price, year, maxSpeed);
        this.elevation = elevation;
        this.maxPassengers = maxPassengers;
    }

    public double getElevation() {
        return elevation;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public int maxFlightSpeed() {
        return maxSpeed;
    }
}
