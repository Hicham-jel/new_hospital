package ma.emsi.test;

public class FritesDecorator extends TajineDecorateAbstrait{
    public FritesDecorator(Tajine decoratedTajine) {
        super(decoratedTajine);
    }

    @Override
    public String getDescription() {
        return decoratedTajine.getDescription()+"avec Frites";
    }

    @Override
    public double cost() {
        return decoratedTajine.cost()+13.0;
    }
}
