package ca.jbrains.pos;

public class Price {
    private final int centsValue;

    public Price(int centsValue) {
        this.centsValue = centsValue;
    }

    public static Price cents(int centsValue) {
        return new Price(centsValue);
    }

    public int centsValue() {
        return centsValue;
    }

    public double euroValue() {
        return centsValue / 100.0d;
    }

    @Override
    public String toString() {
        return String.format("a Price[cents = %d]", centsValue);
    }
}
