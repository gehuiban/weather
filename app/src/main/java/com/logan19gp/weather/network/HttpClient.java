package com.logan19gp.weather.network;

        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * Created by george on 11/3/2016.
 */
public class HttpClient {
    private static final String API_BASE_URL = "http://api.openweathermap.org/";
    private static final String HTTP_GET = "GET";

    public static String getWeatherData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;
        String path = API_BASE_URL + location;
        Log.d("path", path);
        try {
            con = (HttpURLConnection) (new URL(path)).openConnection();
            con.setRequestMethod(HTTP_GET);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }
}