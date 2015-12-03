package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class ReadBarcodesFromAReaderTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    private final BarcodeScannedListener barcodeScannedListener
            = context.mock(BarcodeScannedListener.class);

    @Test
    public void oneBarcode() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode::"));
        }});

        process(new StringReader("::barcode::"));
    }

    private void process(Reader textCommandSource) throws IOException {
        final String line = new BufferedReader(textCommandSource).readLine();
        barcodeScannedListener.onBarcode(line);
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
