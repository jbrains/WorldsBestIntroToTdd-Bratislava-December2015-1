package ca.jbrains.pos;

public interface ShopcartModel {
    void onProductPlacedOnHold(Price price);

    Price getTotal();

    boolean isPurchaseInProgress();
}
