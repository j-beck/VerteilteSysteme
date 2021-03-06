package ch.ethz.inf.vs.a2.sensor;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.ethz.inf.vs.a2.sensor.AbstractSensor;

/**
 * Created by johannes on 15.10.16.
 */

public class TextSensor extends AbstractSensor {

    private final String urlString;

    public TextSensor(String url){
        this.urlString = url;
    }

    @Override
    public String executeRequest() throws Exception {
        String result = null;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        connection.setRequestProperty("Connection", "close");
        try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            result = in.readLine();
        }
        return result;
    }

    @Override
    public double parseResponse(String response) {
        return Double.parseDouble(response);
    }
}
