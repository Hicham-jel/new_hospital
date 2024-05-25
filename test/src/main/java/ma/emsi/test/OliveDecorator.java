package ma.emsi.test;

public class OliveDecorator extends  TajineDecorateAbstrait{
    public OliveDecorator(Tajine decoratedTajine) {
        super(decoratedTajine);
    }

    @Override
    public String getDescription() {
        return decoratedTajine.getDescription()+" avec des olives";
    }

    @Override
    public double cost() {
        return decoratedTajine.cost()+10.0;
    }
}
