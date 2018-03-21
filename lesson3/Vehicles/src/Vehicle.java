
public abstract class Vehicle {
    double longtitude, latitude;
    long price;
    int year;
    int maxSpeed;
    Engine engine;
    static class Engine
    {
        int horsePowers;
        String fuel;

        public Engine(int horsePowers, String fuel) {
            this.horsePowers = horsePowers;
            this.fuel = fuel;
        }
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                ", price=" + price +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Vehicle(long price, int year, int maxSpeed) {
        this(0, 0, price, year, maxSpeed);
    }
    public Vehicle(double longtitude, double latitude, long price, int year, int maxSpeed) {

        this(longtitude, latitude, price, year, maxSpeed, 100, "gas");
    }
    public Vehicle(double longtitude, double latitude, long price, int year, int maxSpeed, int horsePowers, String fuel) {

        this.longtitude = longtitude;
        this.latitude = latitude;
        this.price = price;
        this.year = year;
        this.maxSpeed = maxSpeed;
        this.engine=new Engine(horsePowers, fuel);
    }

    public long getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}
