package ca.jbrains.pos;

import java.io.InputStreamReader;
import java.util.Collections;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) {
        final CommandProcessor commandProcessor = new CommandProcessor(
                new BarcodeScannedController(
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

                            @Override
                            public void displayTotal(Price total) {
                                throw new UnsupportedOperationException("Not yet implemented.");
                            }
                        },
                        null));

        commandProcessor.processCommands(new InputStreamReader(System.in));
    }
}
