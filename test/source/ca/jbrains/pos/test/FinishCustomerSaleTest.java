package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.SellMultipleItemsController;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class FinishCustomerSaleTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void zeroBarcodes() throws Exception {
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            never(display);
        }});

        final SellMultipleItemsController controller
                = new SellMultipleItemsController(null, display);
        controller.onTotal();
    }

    @Test
    public void oneBarcode() throws Exception {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final Price price = Price.cents(750);

        context.checking(new Expectations() {{
            ignoring(display).displayPrice(with(any(Price.class)));

            allowing(catalog).findPrice(with("12345"));
            will(returnValue(price));

            oneOf(display).displayTotal(price);
        }});

        final SellMultipleItemsController controller
                = new SellMultipleItemsController(catalog, display);
        controller.onBarcode("12345");
        controller.onTotal();
    }
}
