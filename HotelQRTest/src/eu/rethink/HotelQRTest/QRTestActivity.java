package eu.rethink.HotelQRTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRTestActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private View placeHolder;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // green placeholder view
        placeHolder = findViewById(R.id.view_qr_placeholder);

        // qr scanner view
        mScannerView = new ZXingScannerView(QRTestActivity.this);   // Programmatically initialize the scanner view
        mScannerView.setVisibility(View.VISIBLE);


        // replace placeholder with scanner view
        replaceView(placeHolder, mScannerView);
        mScannerView.setResultHandler(QRTestActivity.this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume

    }

    @Override
    public void handleResult(Result rawResult) {
        System.out.println("Got scan result!" + rawResult);

        // TODO: parse url from result
        // TODO: make get request on url
        // TODO: parse response payload
        // TODO: set up wifi connection based on configuration in response payload
        // don't forget to add all needed permissions to AndroidManifest!
    }

    /**
     * Replaces a view with another view.
     * @param currentView
     * @param newView
     */
    private static void replaceView(View currentView, View newView) {
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent == null) {
            return;
        }
        final int index = parent.indexOfChild(currentView);
        parent.removeViewAt(index);
        parent.addView(newView, index);
    }
}
