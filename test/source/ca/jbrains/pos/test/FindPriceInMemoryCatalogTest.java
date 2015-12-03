package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

import java.util.HashMap;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog createCatalogWith(String barcode, Price price) {
        return new InMemoryCatalog(
                new HashMap() {{
                    put("definitely not " + barcode, new Price(1));
                    put(barcode, price);
                    put("not " + barcode, new Price(2));
                    put("anything in the world except " + barcode, new Price(3));
                }});
    }

    @Override
    protected InMemoryCatalog createCatalogWithout(final String barcode) {
        return new InMemoryCatalog(
                new HashMap() {{
                    put("definitely not " + barcode, new Price(1));
                    put("not " + barcode, new Price(2));
                    put("anything in the world except " + barcode, new Price(3));
                }});
    }

}
