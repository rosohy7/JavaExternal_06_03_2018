
public abstract class Vehicle {
    double longtitude, latitude;
    long price;
    int year;
    int maxSpeed;

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

        this.longtitude = longtitude;
        this.latitude = latitude;
        this.price = price;
        this.year = year;
        this.maxSpeed = maxSpeed;
    }

    public long getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}
