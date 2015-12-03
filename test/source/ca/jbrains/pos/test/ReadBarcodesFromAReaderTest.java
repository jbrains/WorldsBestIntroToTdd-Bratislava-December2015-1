package ca.jbrains.pos.test;

import ca.jbrains.pos.BarcodeScannedListener;
import ca.jbrains.pos.CommandProcessor;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

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

    @Test
    public void ignoreEmptyLines() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 1::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 2::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 3::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 4::"));
        }});

        process(new StringReader(
                "::barcode 1::\n\n" +
                        "::barcode 2::\n\n\n\n\n" +
                        "::barcode 3::\n\n\n" +
                        "::barcode 4::\n\n\n\n\n\n\n\n\n"));
    }

    private void process(Reader textCommandSource) throws IOException {
        new CommandProcessor(barcodeScannedListener).processCommands(textCommandSource);
    }
}
