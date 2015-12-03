package ca.jbrains.pos;

import java.util.Collections;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) {
        final SellOneItemController controller = new SellOneItemController(
                new InMemoryCatalog(
                        Collections.singletonMap("8586009260819", Price.cents(70))
                ),
                new Display() {
                    @Override
                    public void displayPrice(Price price) {
                        System.out.println(String.format("EUR %.2f", price.euroValue()));
                    }

                    @Override
                    public void displayProductNotFoundMessage(String barcodeNotFound) {
                        System.out.println(String.format("Product not found for %s", barcodeNotFound));
                    }
                }
        );
        controller.onBarcode("12345");
        controller.onBarcode("8586009260819");
    }
}
