package eu.rethink.hotelqr.wifiinfo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Simple http server that holds wifi configuration (ssid & passphrase)
 */
public class WifiInfoServer {

    public static void main(String[] args) {
        try {
            String ssid = "mySSID";
            String presharedkey = "myPassphrase";

            if (args.length > 0)
                ssid = args[0];

            if (args.length > 1)
                presharedkey = args[1];

            System.out.println("Starting WifiInfoServer on: " + InetAddress.getLocalHost().getHostAddress() + ":8000");

            HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
            final String finalSsid = ssid;
            final String finalPresharedkey = presharedkey;
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange httpExchange) throws IOException {
                    System.out.println("New request from " + httpExchange.getRemoteAddress());
                    String jsonResponse = "{'ssid':'" + finalSsid + "', 'presharedkey':'" + finalPresharedkey + "'}";
                    httpExchange.sendResponseHeaders(200,jsonResponse.length());
                    OutputStream responseBody = httpExchange.getResponseBody();
                    responseBody.write(jsonResponse.getBytes());
                    responseBody.close();
                }
            });

            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
