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

    @Test
    public void noBarcodes() throws Exception {
        context.checking(new Expectations() {{
        }});

        process(new StringReader(""));
    }

    @Test
    public void manyBarcodes() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 1::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 2::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 3::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 4::"));
        }});

        process(new StringReader(
                "::barcode 1::\n" +
                        "::barcode 2::\n" +
                        "::barcode 3::\n" +
                        "::barcode 4::"));
    }

    private void process(Reader textCommandSource) throws IOException {
        final BufferedReader bufferedReader
                = new BufferedReader(textCommandSource);

        bufferedReader.lines().forEach(
                (line) -> barcodeScannedListener.onBarcode(line)
        );
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
