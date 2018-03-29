package external.letiuka.model;

import external.letiuka.util.ArrayList;

public class TruckLoader {
    CoffeeTruck truck;


    public TruckLoader() {
    }

    public TruckLoader(CoffeeTruck truck) {
        this.truck = truck;
    }

    public void setTruck(CoffeeTruck truck) {
        this.truck = truck;
    }

    public CoffeeTruck getTruck() {
        return truck;
    }


    public ArrayList findQuality(int min, int max, boolean unload) {
        if (truck == null)
            return new ArrayList();
        ArrayList selectedItems = new ArrayList();
        for (int i = 0; i < truck.getCargoCount(); ++i) {
            CoffeeContainer cofcon = truck.seeCargo(i);
            int quality = cofcon.getCoffee().getQuality();
            if (quality >= min && quality <= max) {
                selectedItems.add(cofcon);
                if (unload) truck.takeCargo(i);
            }
        }
        return selectedItems;
    }

}
