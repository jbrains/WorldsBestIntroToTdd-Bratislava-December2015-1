package ca.jbrains.pos;

public interface BarcodeScannedListener {
    // CONTRACT
    // barcode cannot be empty
    void onBarcode(String barcode);
}
