package ca.jbrains.pos;

public class TotalPressedController {
    private final Display display;
    private final ShopcartModel shopcartModel;

    public TotalPressedController(Display display, ShopcartModel shopcartModel) {
        this.display = display;
        this.shopcartModel = shopcartModel;
    }

    public void onTotal() {
        final boolean isPurchaseInProgress = shopcartModel.isPurchaseInProgress();
        if (isPurchaseInProgress)
            display.displayTotal(shopcartModel.getTotal());
    }
}
