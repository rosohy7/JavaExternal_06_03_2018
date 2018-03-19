public class Car extends Vehicle implements IMoveOnLand {

    @Override
    public int maxMoveOnLandSpeed() {
            return maxSpeed;
    }

    public Car(long price, int year, int maxSpeed) {
        this(0,0,price, year, maxSpeed);
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public Car(double longtitude, double latitude, long price, int year, int maxSpeed) {
        super(longtitude, latitude, price, year, maxSpeed);
    }
}
