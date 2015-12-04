package ca.jbrains.pos;

public class SellMultipleItemsController implements BarcodeScannedListener {
    private final Catalog catalog;
    private final Display display;

    private Price priceOfProductScanned;

    public SellMultipleItemsController(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }

    // CONTRACT
    // barcode cannot be empty
    public void onBarcode(String barcode) {
        final Price price = catalog.findPrice(barcode);
        this.priceOfProductScanned = price;
        if (price == null)
            display.displayProductNotFoundMessage(barcode);
        else
            display.displayPrice(price);
    }

    public void onTotal() {
        // This method intentionally left blank
        if (priceOfProductScanned != null)
            display.displayTotal(priceOfProductScanned);
    }
}
