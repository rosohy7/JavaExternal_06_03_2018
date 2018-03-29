package external.letiuka.controller;

import util.ArrayList;
import external.letiuka.model.TruckLoader;
import external.letiuka.exceptions.TruckTooFullException;
import external.letiuka.model.CoffeeContainer;
import external.letiuka.model.CoffeeFactory;
import external.letiuka.model.CoffeeTruck;
import external.letiuka.serialization.TruckIO;

import java.io.IOException;

public class Controller {
    TruckLoader loader;
    TruckIO trio;
    CoffeeTruck truck;
    CoffeeFactory fact;

    public Controller() {
        trio = new TruckIO();
        loader = new TruckLoader();
        fact = new CoffeeFactory();
        truck = new CoffeeTruck(0);
        loader.setTruck(truck);
    }

    public void createTruck(double volume) {
        truck = new CoffeeTruck(volume);
        loader.setTruck(truck);
    }

    public void importTruck(String file) throws IOException, ClassNotFoundException {
        truck = trio.readTruck(file);
        loader.setTruck(truck);
    }

    public void exportTruck(String file) throws IOException {
        trio.writeTruck(truck, file);
    }

    public void addCargo(int type, int form, int quality,
                         double weight, double cost, double vol, int count) throws TruckTooFullException {
        for (int i = 0; i < count; i++) {
            truck.addCargo(new CoffeeContainer(vol, weight, cost, fact.createCoffee(type, quality, form)));
        }

    }

    public void removeCoffee(int index) {
        truck.takeCargo(index);
    }

    public ArrayList selecByQuality(int min, int max,
                                    boolean rmItems) { // if true removes selected items from truck
        return loader.findQuality(min, max, rmItems);
    }

    public String describeTruck() {
        return truck.toString();
    }

    public void sortCoffee() {   //cost to weight ratio
        truck.sortCargo();
    }


}
