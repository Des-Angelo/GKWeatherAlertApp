package com.example.android.gkweatheralert;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import Data.JSONWeatherParser;
import Data.WeatherHttpClient;
import Model.Weather;
import Util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    /**
     * Kingston Variables
     */
    private TextView kCityName;
    private TextView kTemperature;
    private ImageView kIconView;
    private TextView kDescription;
    private TextView kHumidity;
    private TextView kPressure;
    private TextView kWind;

    /**
     * Montego Bay Variables
     */
    private TextView mCityName;
    private TextView mTemperature;
    private ImageView mIconView;
    private TextView mDescription;
    private TextView mHumidity;
    private TextView mPressure;
    private TextView mWind;

    /**
     * Last Updated
     */
    private TextView updated;

    Weather weather = new Weather();

    public HomeFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        kCityName = (TextView) view.findViewById(R.id.king_city_name);
        mCityName = (TextView) view.findViewById(R.id.mont_city_name);

        kTemperature = (TextView) view.findViewById(R.id.king_temperature);
        mTemperature = (TextView) view.findViewById(R.id.mont_temperature);

        kIconView = (ImageView) view.findViewById(R.id.king_weather_icon);
        mIconView = (ImageView) view.findViewById(R.id.mont_weather_icon);

        kDescription = (TextView) view.findViewById(R.id.king_cloud);
        mDescription = (TextView) view.findViewById(R.id.mont_cloud);

        kHumidity = (TextView) view.findViewById(R.id.king_humid);
        mHumidity = (TextView) view.findViewById(R.id.mont_humid);

        kPressure = (TextView) view.findViewById(R.id.king_pres);
        mPressure = (TextView) view.findViewById(R.id.mont_pres);

        kWind = (TextView) view.findViewById(R.id.king_wind);
        mWind = (TextView) view.findViewById(R.id.mont_wind);

        updated = (TextView) view.findViewById(R.id.last_update);

        weatherData();
        return view;

    }

    public void weatherData() {

        WeatherTaskKingston weatherTaskKingston = new WeatherTaskKingston();
        weatherTaskKingston.execute(new String());

        WeatherTaskMontegoBay weatherTaskMontegoBay = new WeatherTaskMontegoBay();
        weatherTaskMontegoBay.execute(new String());

    }

    private class DownloadImageAsyncTaskKingston extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(String... params) {

            bitmap = downloadImage(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            kIconView.setImageBitmap(bitmap);
        }

        private Bitmap downloadImage(String code) {

            Bitmap bitmap = null;
            InputStream is;

            try {
                URL url = new URL(Utils.ICON_URL + code + ".png");

                /**
                 * Creating an http connection to communicate with url
                 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                connection.connect();

                // Reading data from url
                is = connection.getInputStream();

                // Creating a bitmap from the stream returned from the url
                bitmap = BitmapFactory.decodeStream(is);


            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    private class DownloadImageAsyncTaskMontegoBay extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(String... params) {

            bitmap = downloadImage(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mIconView.setImageBitmap(bitmap);
        }

        private Bitmap downloadImage(String code) {

            Bitmap bitmap = null;
            InputStream is;

            try {
                URL url = new URL(Utils.ICON_URL + code + ".png");

                /**
                 * Creating an http connection to communicate with url
                 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                connection.connect();

                // Reading data from url
                is = connection.getInputStream();

                // Creating a bitmap from the stream returned from the url
                bitmap = BitmapFactory.decodeStream(is);


            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    private class WeatherTaskKingston extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {

            String data = ((new WeatherHttpClient()).getWeatherDataKingston());

            weather = JSONWeatherParser.getWeather(data);

            new DownloadImageAsyncTaskKingston().execute(weather.currentCondition.getIcon());

            return weather;
        }


        @Override
        protected void onPostExecute(Model.Weather weather) {
            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance();
            String lastUpdatedDate = df.format(new Date(weather.place.getLastUpdated()));

            DecimalFormat decimalFormat = new DecimalFormat("#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

            kCityName.setText(weather.place.getCity() + ", " + weather.place.getCountry());
            kTemperature.setText(tempFormat + " ∘C");
            kHumidity.setText("Humidity: " + weather.currentCondition.getHumidity() + "%");
            kPressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
            kWind.setText("Wind " + weather.wind.getSpeed() + "mps");
            updated.setText("Last Updated " + lastUpdatedDate);
            kDescription.setText("Condition: " + weather.currentCondition.getCondition() + " ("
                    + weather.currentCondition.getDescription() + ")");
        }
    }

    private class WeatherTaskMontegoBay extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {

            String data = ((new WeatherHttpClient()).getWeatherDataMontegobay());

            weather = JSONWeatherParser.getWeather(data);

            new DownloadImageAsyncTaskMontegoBay().execute(weather.currentCondition.getIcon());

            return weather;
        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance();
            String lastUpdatedDate = df.format(new Date(weather.place.getLastUpdated()));

            DecimalFormat decimalFormat = new DecimalFormat("#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

            mCityName.setText(weather.place.getCity() + ", " + weather.place.getCountry());
            mTemperature.setText(tempFormat + " ∘C");
            mHumidity.setText("Humidity: " + weather.currentCondition.getHumidity() + "%");
            mPressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
            mWind.setText("Wind " + weather.wind.getSpeed() + "mps");
            mDescription.setText("Condition: " + weather.currentCondition.getCondition() + " ("
                    + weather.currentCondition.getDescription() + ")");

            updated.setText("Last Updated: " + lastUpdatedDate);
        }
    }
}

