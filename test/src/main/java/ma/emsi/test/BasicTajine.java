package ma.emsi.test;

public class BasicTajine implements Tajine{

    @Override
    public String getDescription() {
        return "Tajine de Base";
    }

    @Override
    public double cost() {
        return 50.0;
    }
}
