package eu.rethink.hotelqr;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.google.zxing.Result;
import eu.rethink.HotelQRTest.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    public void handleResult(final Result rawResult) {
        System.out.println("Got scan result!" + rawResult);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // parse url from result
                    String url = rawResult.getText();

                    // make get request on url
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(url));
                    System.out.println("sending request to: " + url);

                    HttpResponse response = client.execute(request);

                    // parse response payload
                    StatusLine statusLine = response.getStatusLine();
                    System.out.println("response status line: " + statusLine);

                    String responseString = null;

                    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                        System.out.println("status is OK");

                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.getEntity().writeTo(out);
                        responseString = out.toString();
                        System.out.println("responseString: " + responseString);

                        out.close();
                    } else {
                        System.out.println("status is NOT OK");
                        // Closes the connection.
                        response.getEntity().getContent().close();
                        throw new IOException(statusLine.getReasonPhrase());
                    }

                    JSONObject json = new JSONObject(responseString);
                    System.out.println("parsed json: " + json);

                    System.out.println("ssid: " + json.getString("ssid"));
                    System.out.println("presharedkey: " + json.getString("presharedkey"));


                    // set up wifi connection based on configuration in response payload
                    WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    WifiConfiguration wc = new WifiConfiguration();
                    wc.SSID = "\"" + json.getString("ssid") + "\""; // quotes are important
                    wc.preSharedKey = "\"" + json.getString("presharedkey") + "\""; // quotes are important

                    wc.status = WifiConfiguration.Status.ENABLED;

                    // expand settings for different security protocols
                    wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

                    // connect to and enable the connection
                    int netId = wifiManager.addNetwork(wc);
                    System.out.println("netId: " + netId);
                    boolean success = wifiManager.enableNetwork(netId, true);
                    System.out.println("enableNetwork -> " + success);

                    System.out.println("setWifiEnabled -> " + wifiManager.setWifiEnabled(true));

                    if (success)
                        finish();

                } catch (URISyntaxException | IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

    @Override
    protected void onDestroy() {

        if (mScannerView != null)
            mScannerView.stopCamera();
        super.onDestroy();
    }
}
