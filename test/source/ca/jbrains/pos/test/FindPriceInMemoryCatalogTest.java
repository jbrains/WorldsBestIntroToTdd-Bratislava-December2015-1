package ca.jbrains.pos.test;

import ca.jbrains.pos.test.SellOneItemControllerTest.Catalog;
import ca.jbrains.pos.test.SellOneItemControllerTest.Price;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog createCatalogWith(String barcode, Price price) {
        return new InMemoryCatalog(
                new HashMap() {{
                    put("definitely not " + barcode, new Price());
                    put(barcode, price);
                    put("not " + barcode, new Price());
                    put("anything in the world except " + barcode, new Price());
                }});
    }

    @Override
    protected InMemoryCatalog createCatalogWithout(final String barcode) {
        return new InMemoryCatalog(
                new HashMap() {{
                    put("definitely not " + barcode, new Price());
                    put("not " + barcode, new Price());
                    put("anything in the world except " + barcode, new Price());
                }});
    }

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
