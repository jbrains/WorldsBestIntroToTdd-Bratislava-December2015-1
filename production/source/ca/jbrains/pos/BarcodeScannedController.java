package ca.jbrains.pos;

public class BarcodeScannedController implements BarcodeScannedListener {
    private final Catalog catalog;
    private final Display display;
    private final ShopcartModel shopcartModel;

    public BarcodeScannedController(Catalog catalog, Display display, ShopcartModel shopcartModel) {
        this.catalog = catalog;
        this.display = display;
        this.shopcartModel = shopcartModel;
    }

    // CONTRACT
    // barcode cannot be empty
    public void onBarcode(String barcode) {
        final Price price = catalog.findPrice(barcode);
        if (price == null)
            display.displayProductNotFoundMessage(barcode);
        else {
            shopcartModel.onProductPlacedOnHold(price);
            display.displayPrice(price);
        }
    }

    public void onTotal() {
        final Price total = shopcartModel.getTotal();
        if (0 != total.centsValue())
            display.displayTotal(total);
    }
}
