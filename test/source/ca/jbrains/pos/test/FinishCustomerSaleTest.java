package ca.jbrains.pos.test;

import ca.jbrains.pos.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class FinishCustomerSaleTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void shopcartEmpty() throws Exception {
        final Display display = context.mock(Display.class);
        final ShopcartModel shopcartModel = context.mock(ShopcartModel.class);

        context.checking(new Expectations() {{
            allowing(shopcartModel).getTotal();
            will(returnValue(Price.cents(0)));

            never(display);
        }});

        final BarcodeScannedController controller
                = new BarcodeScannedController(null, display, shopcartModel);
        controller.onTotal();
    }

    @Test
    public void shopcartNotEmpty() throws Exception {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final ShopcartModel shopcartModel = context.mock(ShopcartModel.class);

        final Price amount = Price.cents(750);

        context.checking(new Expectations() {{
            allowing(shopcartModel).getTotal();
            will(returnValue(amount));

            oneOf(display).displayTotal(amount);
        }});

        final BarcodeScannedController controller
                = new BarcodeScannedController(catalog, display, shopcartModel);
        controller.onTotal();
    }

}
