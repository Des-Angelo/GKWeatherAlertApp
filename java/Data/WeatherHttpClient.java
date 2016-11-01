package Data;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by penai on 28/10/2016.
 */

public class WeatherHttpClient {

    public String getWeatherDataKingston(){

        HttpURLConnection kinConnection;
        InputStream inputStream;

        try {
            kinConnection = (HttpURLConnection) new URL(Utils.BASE_URL_KINGSTON + Utils.APP_ID).openConnection();
            kinConnection.setRequestMethod("GET");
            kinConnection.setDoInput(true);
            kinConnection.setDoOutput(true);
            kinConnection.connect();

            // Read the response
            StringBuilder stringBuffer = new StringBuilder();
            inputStream = kinConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null){

                stringBuffer.append(line).append("\r\n"); // Ensures all line read is in its own line.
            }

            inputStream.close();
            kinConnection.disconnect();

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getWeatherDataMontegobay(){

        HttpURLConnection MonConnection;
        InputStream inputStream;

        try {
            MonConnection = (HttpURLConnection) new URL(Utils.BASE_URL_MONTEGO_BAY + Utils.APP_ID).openConnection();
            MonConnection.setRequestMethod("GET");
            MonConnection.setDoInput(true);
            MonConnection.setDoOutput(true);
            MonConnection.connect();

            // Read the reponse
            StringBuilder stringBuffer = new StringBuilder();
            inputStream = MonConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null){

                stringBuffer.append(line).append("\r\n"); // Ensures all line read is in its own line.
            }

            inputStream.close();
            MonConnection.disconnect();

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}
