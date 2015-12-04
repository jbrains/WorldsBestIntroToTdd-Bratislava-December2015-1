package ca.jbrains.pos;

class StupidShopcartModel implements ShopcartModel {
    private Price priceOfProductScanned = Price.cents(0);

    @Override
    public void onProductPlacedOnHold(Price price) {
        this.priceOfProductScanned = price;
    }

    @Override
    public Price getTotal() {
        return priceOfProductScanned;
    }
}
