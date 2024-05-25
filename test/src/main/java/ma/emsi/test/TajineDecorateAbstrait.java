package ma.emsi.test;

abstract class TajineDecorateAbstrait implements Tajine {
protected  Tajine decoratedTajine;

    public TajineDecorateAbstrait(Tajine decoratedTajine) {
        this.decoratedTajine = decoratedTajine;
    }
}
