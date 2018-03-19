public class VehicleArray {
    Vehicle[] vehicles;

    public VehicleArray(int len) {
        vehicles = new Vehicle[len];
        VehicleFactory factory = new VehicleFactory();
        for (int i = 0; i < vehicles.length; ++i) {
            vehicles[i] = factory.createRandVehicle();
            System.out.println("Generating " + vehicles[i] + "...");
        }
        System.out.println();

    }

    public Vehicle getMostExpensive() {
        if (vehicles.length == 0) return null;
        Vehicle res = vehicles[0];
        for (int i = 0; i < vehicles.length; ++i) {
            if (vehicles[i].price > res.price) res = vehicles[i];
        }

        return res;
    }

    public Vehicle getCheapestOfBest() {
        Vehicle res = null;
        for (int i = 0; i < vehicles.length; ++i) {
            if (vehicles[i].maxSpeed >= 150 && vehicles[i].year <= 2005 && vehicles[i].year >= 2000) {
                if (res == null || vehicles[i].price < res.price) res = vehicles[i];
            }
        }
        return res;
    }

    public Car[] getNewCars() {
        Car[] res;
        int count = 0;
        for (int i = 0; i < vehicles.length; ++i) {
            if (vehicles[i] instanceof Car && vehicles[i].year >= 2013) {
                ++count;
            }
        }

        res = new Car[count];
        count = 0;
        for (int i = 0; i < vehicles.length; ++i) {
            if (vehicles[i] instanceof Car && vehicles[i].year >= 2013) {
                res[count++] = (Car) vehicles[i];
            }
        }
        return res;
    }

    public Ship[] getNewShipsSorted() {
        Ship[] res;
        int count = 0;
        for (int i = 0; i < vehicles.length; ++i) {
            if (vehicles[i] instanceof Ship && vehicles[i].year >= 2013) {
                ++count;
            }
        }

        res = new Ship[count];
        count = 0;
        for (int i = 0; i < vehicles.length; ++i) {
            if (vehicles[i] instanceof Ship && vehicles[i].year >= 2013) {
                res[count++] = (Ship) vehicles[i];
            }
        }
        for (int i = count - 1; i > 0; --i)
            for (int k = 0; k < i; ++k) {
                if (res[k].price < res[k + 1].price) {
                    Ship tship=res[k];
                    res[k]=res[k+1];
                    res[k+1]=tship;
                }
            }
        return res;
    }

    public void printVehicleList(Vehicle[] vehicles) {
        if (vehicles.length == 0) System.out.println("No vehicles to show");
        for (int i = 0; i < vehicles.length; ++i) {
            System.out.println(i + 1 + ". " + vehicles[i]);
        }
    }

}
