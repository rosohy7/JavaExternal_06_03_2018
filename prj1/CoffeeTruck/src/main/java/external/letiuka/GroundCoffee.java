package external.letiuka;

public class GroundCoffee extends Coffee {
    public GroundCoffee(int type, int quality) {
        super(type, quality);
    }

    @Override
    public Coffee clone() {
        return new GroundCoffee(type, quality);
    }

    @Override
    public String toString() {
        return typeToString() + " ground coffee of " + quality + " / 100 quality";
    }
}
