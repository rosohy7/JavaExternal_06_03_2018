package external.letiuka;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TruckLoaderTest
        extends Assert {
    private static final TruckLoader loader = new TruckLoader();
    private static ArrayList list;
    private static CoffeeTruck truck;
    private static CoffeeContainer cont1, cont2, cont3;
    private static Coffee coffee1, coffee2, coffee3;

    @BeforeClass
    public static void setup() {
        truck = mock(CoffeeTruck.class);
        cont1 = mock(CoffeeContainer.class);
        cont2 = mock(CoffeeContainer.class);
        cont3 = mock(CoffeeContainer.class);
        coffee1 = mock(InstantCoffeeInCans.class);
        coffee2 = mock(GroundCoffee.class);
        coffee3 = mock(GroundCoffee.class);
        when(coffee1.getQuality()).thenReturn(1);
        when(coffee2.getQuality()).thenReturn(50);
        when(coffee3.getQuality()).thenReturn(100);
        when(cont1.getCoffee()).thenReturn(coffee1);
        when(cont2.getCoffee()).thenReturn(coffee2);
        when(cont3.getCoffee()).thenReturn(coffee3);
        loader.setTruck(truck);
        when(truck.seeCargo(0)).thenReturn(cont1);
        when(truck.seeCargo(1)).thenReturn(cont2);
        when(truck.seeCargo(2)).thenReturn(cont3);
        when(truck.getCargoCount()).thenReturn(3);
    }

    @Test
    public void selectsCorrectCoffee() {
        list = loader.findQuality(1, 50, false);
        assertEquals(2, list.getLen());
        list = loader.findQuality(2, 49, false);
        assertEquals(0, list.getLen());
        list = loader.findQuality(30, 60, false);
        assertEquals(1, list.getLen());
        assertEquals(50, ((CoffeeContainer) list.get(0)).getCoffee().getQuality());
        list = loader.findQuality(100, 100, false);
        assertEquals(1, list.getLen());
        assertEquals(100, ((CoffeeContainer) list.get(0)).getCoffee().getQuality());

    }

    @Test
    public void removesItemsWhenAsked() {
        list = loader.findQuality(1, 50, true);
        verify(truck).takeCargo(0);
        list = loader.findQuality(100, 100, false);
        verify(truck, never()).takeCargo(2);

    }
}
