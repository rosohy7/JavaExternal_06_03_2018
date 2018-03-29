package external.letiuka;

import java.io.Serializable;

public class CoffeeContainer implements Comparable, Cloneable, Serializable {
    static long serialVersionUID = 1;
    private double volume;
    private double coffeeWeight;
    private double coffeeCost;
    private Coffee coffee;

    public CoffeeContainer(double volume) {
        this(volume, 0, 0, new NoCoffee());
    }

    public CoffeeContainer(double volume, double coffeeWeight, double coffeeCost, Coffee coffee) {
        final double EPS = 0.000001;
        if (coffee instanceof NoCoffee) {
            if (coffeeWeight > EPS || coffeeCost > EPS) throw new IllegalArgumentException();
        }
        if (coffee == null) throw new IllegalArgumentException();
        if (volume < -EPS || coffeeWeight < -EPS || coffeeCost < -EPS) throw new IllegalArgumentException();
        this.volume = volume;
        this.coffeeWeight = coffeeWeight;
        this.coffeeCost = coffeeCost;
        this.coffee = coffee;
    }

    public int compareTo(Object obj) {
        CoffeeContainer container = (CoffeeContainer) obj;
        return (int) (getCoffeeCost() * container.getCoffeeWeight()
                - container.getCoffeeCost() * getCoffeeWeight());
    }

    @Override
    protected Object clone() {
        return new CoffeeContainer(volume, coffeeWeight, coffeeCost, coffee.clone());
    }

    public double getCoffeeWeight() {
        return coffeeWeight;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public double getVolume() {
        return volume;
    }

    public double getCoffeeCost() {
        return coffeeCost;
    }

    public void setCoffeeWeight(double coffeeWeight) {
        this.coffeeWeight = coffeeWeight;
    }

    public void setCoffeeCost(double coffeeCost) {
        this.coffeeCost = coffeeCost;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String toString() {
        return "Coffee container that takes "
                + volume + " m^3 of space, costs "
                + coffeeCost + " and contains "
                + coffeeWeight + " kilos of "
                + coffee.toString();

    }
}
