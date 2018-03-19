import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        VehicleArray vehicles=new VehicleArray(50);

        Vehicle tveh=vehicles.getMostExpensive();
        System.out.println("The most expensive vehicle is "+(tveh==null ? "not available":tveh));
        System.out.println();

        tveh=vehicles.getCheapestOfBest();
        System.out.println("The cheapest vehicle that is faster than 150 kmh and is created between 2000 and 2005 is "
                + (tveh==null ? "not available":tveh));
        System.out.println();

        System.out.println("List of all new cars:");
        vehicles.printVehicleList(vehicles.getNewCars());
        System.out.println();

        System.out.println("List of all new ships from expensive to cheap:");
        vehicles.printVehicleList(vehicles.getNewShipsSorted());
        System.out.println();

        MoveLandArray landGoers=new MoveLandArray(vehicles.vehicles);
        landGoers.sortSpeed();
        landGoers.print();

        SwimArray swimmers = new SwimArray(vehicles.vehicles);
        swimmers.sortSpeed();
        swimmers.print();

        FlyArray fliers = new FlyArray(vehicles.vehicles);
        fliers.sortSpeed();
        fliers.print();
    }
}