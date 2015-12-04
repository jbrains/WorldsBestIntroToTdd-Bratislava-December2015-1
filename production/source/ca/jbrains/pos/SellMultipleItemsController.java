package ca.jbrains.pos;

public class SellMultipleItemsController implements BarcodeScannedListener {
    private final Catalog catalog;
    private final Display display;

    public SellMultipleItemsController(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }

    // CONTRACT
    // barcode cannot be empty
    public void onBarcode(String barcode) {
        final Price price = catalog.findPrice(barcode);
        if (price == null)
            display.displayProductNotFoundMessage(barcode);
        else
            display.displayPrice(price);
    }

    public void onTotal() {
        // This method intentionally left blank
    }
}
