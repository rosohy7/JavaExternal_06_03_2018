package external.letiuka.model;

import external.letiuka.util.ArrayList;
import external.letiuka.exceptions.TruckTooFullException;

import java.io.Serializable;

public class CoffeeTruck implements Serializable {
    private ArrayList allCargo;
    static long serialVersionUID = 1;
    double volume, filledVolume;  // in cubic meters
    double cargoCost;             // in UAH
    int cargoCount;

    public CoffeeTruck(double volume) {
        this.volume = volume;
        if (volume < 0) throw new IllegalArgumentException();
        allCargo = new ArrayList();
    }

    public double getVolume() {
        return volume;
    }

    public double getFilledVolume() {
        return filledVolume;
    }

    public double getCargoCost() {
        return cargoCost;
    }

    public int getCargoCount() {
        return cargoCount;
    }

    public void sortCargo() {
        allCargo.sort();
    }

    public CoffeeContainer seeCargo(int index) {
        return (CoffeeContainer) ((CoffeeContainer) allCargo.get(index)).clone();
    }

    public CoffeeContainer takeCargo(int index) {
        CoffeeContainer cof = (CoffeeContainer) allCargo.get(index);
        if (allCargo.remove(index)) {
            cargoCost -= cof.getCoffeeCost();
            filledVolume -= cof.getVolume();
            cargoCount--;
            return cof;
        }
        return null;
    }

    public void addCargo(CoffeeContainer coffeeContainer) throws TruckTooFullException {
        if (coffeeContainer.getVolume() < volume - filledVolume) {
            allCargo.add(coffeeContainer.clone());
            cargoCost += coffeeContainer.getCoffeeCost();
            filledVolume += coffeeContainer.getVolume();
            cargoCount++;
            coffeeContainer = null;
        } else throw new TruckTooFullException();
    }

    @Override
    public String toString() {
        return "CoffeeTruck with "
                + filledVolume + " out of "
                + volume + " m^3 filled for the total cargo cost of "
                + cargoCost + " UAH with following items: \n"
                + allCargo.toString();
    }
}
