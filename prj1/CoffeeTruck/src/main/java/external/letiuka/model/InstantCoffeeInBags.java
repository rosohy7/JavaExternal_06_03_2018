package external.letiuka.model;

public class InstantCoffeeInBags extends InstantCoffee {
    public InstantCoffeeInBags(int type, int quality) {
        super(type, quality);
    }

    @Override
    public InstantCoffeeInBags clone() {
        return new InstantCoffeeInBags(type, quality);
    }

    @Override
    public String toString() {
        return typeToString() + " instant coffee in bags of " + quality + " / 100 quality";
    }
}
