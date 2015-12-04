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
            allowing(shopcartModel).isPurchaseInProgress();
            will(returnValue(false));

            never(display);
        }});

        final TotalPressedController controller
                = new TotalPressedController(display, shopcartModel);
        controller.onTotal();
    }

    @Test
    public void shopcartNotEmpty() throws Exception {
        final Display display = context.mock(Display.class);
        final ShopcartModel shopcartModel = context.mock(ShopcartModel.class);

        final Price amount = Price.cents(750);

        context.checking(new Expectations() {{
            allowing(shopcartModel).isPurchaseInProgress();
            will(returnValue(true));

            allowing(shopcartModel).getTotal();
            will(returnValue(amount));

            oneOf(display).displayTotal(amount);
        }});

        final TotalPressedController controller
                = new TotalPressedController(display, shopcartModel);
        controller.onTotal();
    }
}
