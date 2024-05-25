package ma.emsi.test;

public class CitronDecorator extends  TajineDecorateAbstrait{
    public CitronDecorator(Tajine decoratedTajine) {
        super(decoratedTajine);
    }

    @Override
    public String getDescription() {
        return decoratedTajine.getDescription()+" avec citrons";
    }

    @Override
    public double cost() {
        return decoratedTajine.cost()+15.0;
    }
}
