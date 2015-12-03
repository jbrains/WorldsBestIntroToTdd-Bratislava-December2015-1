package ca.jbrains.pos.test;

import ca.jbrains.pos.test.SellOneItemControllerTest.Catalog;
import ca.jbrains.pos.test.SellOneItemControllerTest.Price;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        final Price price = Price.cents(1250);

        final InMemoryCatalog catalog = new InMemoryCatalog(
                Collections.singletonMap("12345", price));

        Assert.assertEquals(price, catalog.findPrice("12345"));
    }

    @Test
    public void productFoundAmongOthers() throws Exception {
        final Price price = Price.cents(1250);

        final Map pricesByBarcode = new HashMap() {{
            put("definitely not 12345", new Price());
            put("not 12345", new Price());
            put("12345", price);
            put("anything in the world except 12345", new Price());
        }};
        // Checking a precondition
        Assert.assertNotEquals("12345", pricesByBarcode.keySet().iterator().next());

        final InMemoryCatalog catalog = new InMemoryCatalog(pricesByBarcode);

        Assert.assertEquals(price, catalog.findPrice("12345"));
    }

    @Test
    public void productNotFound() throws Exception {
        final InMemoryCatalog catalog = new InMemoryCatalog(
                new HashMap() {{
            put("definitely not 12345", new Price());
            put("not 12345", new Price());
            put("anything in the world except 12345", new Price());
        }});

        Assert.assertEquals(null, catalog.findPrice("12345"));
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
