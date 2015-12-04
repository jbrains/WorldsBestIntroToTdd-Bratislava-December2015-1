package ca.jbrains.pos.test;

import ca.jbrains.pos.Display;
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
}
