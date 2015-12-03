package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;
import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        final Price price = Price.cents(1250);
        final Catalog catalog = createCatalogWith("12345", price);
        Assert.assertEquals(price, catalog.findPrice("12345"));
    }

    protected abstract Catalog createCatalogWith(String barcode, Price price);

    @Test
    public void productNotFound() throws Exception {
        final Catalog catalog = createCatalogWithout("12345");
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    protected abstract Catalog createCatalogWithout(String barcode);
}
