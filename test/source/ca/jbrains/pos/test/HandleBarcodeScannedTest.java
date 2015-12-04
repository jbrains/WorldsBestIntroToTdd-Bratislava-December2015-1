package ca.jbrains.pos.test;

import ca.jbrains.pos.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class HandleBarcodeScannedTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void productFound() throws Exception {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final ShopcartModel shopcartModel = context.mock(ShopcartModel.class);

        final Price price = Price.cents(795);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(price));

            oneOf(display).displayPrice(with(price));
            oneOf(shopcartModel).onProductPlacedOnHold(with(price));
        }});

        final BarcodeScannedController controller
                = new BarcodeScannedController(catalog, display, shopcartModel);
        controller.onBarcode("12345");
    }

    @Test
    public void productNotFound() throws Exception {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(null));

            oneOf(display).displayProductNotFoundMessage(with("12345"));
        }});

        final BarcodeScannedController controller
                = new BarcodeScannedController(catalog, display, null);
        controller.onBarcode("12345");
    }
}
