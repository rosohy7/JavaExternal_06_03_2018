package external.letiuka;

import external.letiuka.exceptions.TruckTooFullException;
import external.letiuka.model.CoffeeBeans;
import external.letiuka.model.CoffeeContainer;
import external.letiuka.model.CoffeeTruck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoffeeTruckTest extends Assert {
    private CoffeeTruck truck = new CoffeeTruck(100);

    @Before
    public void empty() {
        truck = new CoffeeTruck(100);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 1000)
    public void negativeVolumeThrows() throws TruckTooFullException {
        truck = new CoffeeTruck(-1);
    }

    @Test
    public void countsTotalCost() throws TruckTooFullException {
        double EPS = 0.00001;
        truck.addCargo(new CoffeeContainer(2, 2, 2, new CoffeeBeans(3, 2)));
        truck.addCargo(new CoffeeContainer(2, 2, 2, new CoffeeBeans(3, 2)));
        assertTrue("Total cargo cost is 4",Math.abs(4-truck.getCargoCost())<EPS);
        truck.takeCargo(0);
        assertTrue("Total cargo cost is 2",Math.abs(2-truck.getCargoCost())<EPS);
    }

    @Test(expected = TruckTooFullException.class, timeout = 1000)
    public void overloadTruckThrows() throws TruckTooFullException {
        truck.addCargo(new CoffeeContainer(70, 1, 1, new CoffeeBeans(1, 10)));
        truck.addCargo(new CoffeeContainer(31, 1, 1, new CoffeeBeans(1, 10)));
    }

    @Test
    public void protectsCargoFromChange() throws TruckTooFullException {
        double EPS = 0.00001;
        CoffeeContainer cont=new CoffeeContainer(2, 2, 2, new CoffeeBeans(3, 2));
        truck.addCargo(cont);
        truck.addCargo(cont);
        cont = new CoffeeContainer(2, 2, 20, new CoffeeBeans(3, 2));
        assertTrue("Total cargo cost is 4",Math.abs(4-truck.getCargoCost())<EPS);
        truck.takeCargo(0);
        assertTrue("Total cargo cost is 2",Math.abs(2-truck.getCargoCost())<EPS);
    }


}
