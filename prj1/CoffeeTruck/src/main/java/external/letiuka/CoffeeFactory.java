package external.letiuka;

public class CoffeeFactory {
    public static final int BEANS = 1;
    public static final int GROUND = 2;
    public static final int INSTANT_BAGS = 3;
    public static final int INSTANT_CANS = 4;

    public Coffee createCoffee(int type, int quality, int form) {
        if (form == BEANS) return new CoffeeBeans(type, quality);
        else if (form == GROUND) return new GroundCoffee(type, quality);
        else if (form == INSTANT_BAGS) return new InstantCoffeeInBags(type, quality);
        else if (form == INSTANT_CANS) return new InstantCoffeeInCans(type, quality);
        else return null;
    }
}
