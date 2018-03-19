public class Ship extends Vehicle implements ISwim {
    int maxPassengers;
    String homePort;

    public Ship(long price, int year, int maxSpeed, int maxPassengers, String homePort) {
        this(0,0, price, year, maxSpeed,maxPassengers,homePort);
    }

    public Ship(double longtitude, double latitude, long price, int year, int maxSpeed, int maxPassengers, String homePort) {
        super(longtitude, latitude, price, year, maxSpeed);
        this.maxPassengers = maxPassengers;
        this.homePort = homePort;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "maxPassengers=" + maxPassengers +
                ", homePort='" + homePort + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getHomePort() {
        return homePort;
    }

    @Override

    public int maxSwimSpeed() {
        return maxSpeed;
    }
}
