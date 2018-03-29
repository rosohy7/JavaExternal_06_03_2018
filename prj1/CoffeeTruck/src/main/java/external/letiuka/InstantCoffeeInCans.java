package external.letiuka;

public class InstantCoffeeInCans extends InstantCoffee {
    public InstantCoffeeInCans(int type, int quality) {
        super(type, quality);
    }

    @Override
    public InstantCoffeeInCans clone() {
        return new InstantCoffeeInCans(type, quality);
    }

    @Override
    public String toString() {
        return typeToString() + " instant coffee in cans of " + quality + " / 100 quality";
    }
}
