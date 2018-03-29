package external.letiuka;

public class NoCoffee extends Coffee {
    public NoCoffee()
    {
        super(Coffee.EMPTY,-1);
    }

    @Override
    public NoCoffee clone() {
        return new NoCoffee();
    }
    @Override
    public String toString() {
        return "nothing";
    }
}
