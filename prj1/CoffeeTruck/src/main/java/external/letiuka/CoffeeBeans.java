package external.letiuka;

public class CoffeeBeans extends Coffee {
    public CoffeeBeans(int type, int quality) {
        super(type, quality);
    }

    @Override
    public Coffee clone() {
        return new CoffeeBeans(type, quality);
    }

    @Override
    public String toString() {
        return typeToString() + " coffee beans of " + quality + " / 100 quality";
    }

}
