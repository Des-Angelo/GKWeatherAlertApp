package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.gkweatheralert.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Data.JSONWeatherParser;
import Model.Database.DBHelper;
import Model.Employee;
import Model.Weather;
import Util.Utils;


import static android.R.attr.data;


/**
 * Created by penai on 30/10/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> {

    private ArrayList<Employee> data;

    public RecyclerAdapter(ArrayList<Employee> data) {

        this.data = data;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title;
        TextView description;
        TextView date;
        ImageView notificationIcon;

        PersonViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
            notificationIcon = (ImageView) itemView.findViewById(R.id.notification_icon);
        }
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {

        DBHelper dbHelper = null;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

//        HttpURLConnection MonConnection;
//        InputStream inputStream;
//
//        try {
//            MonConnection = (HttpURLConnection) new URL(Utils.BASE_URL_KINGSTON + Utils.APP_ID).openConnection();
//            MonConnection.setRequestMethod("GET");
//            MonConnection.setDoInput(true);
//            MonConnection.setDoOutput(true);
//            MonConnection.connect();
//
//            // Read the reponse
//            StringBuilder stringBuffer = new StringBuilder();
//            inputStream = MonConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null){
//
//                stringBuffer.append(line).append("\r\n"); // Ensures all line read is in its own line.
//            }
//
//            inputStream.close();
//            MonConnection.disconnect();
//
//            String url = stringBuffer.toString();
//
//            Weather data = JSONWeatherParser.getWeather(url);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        holder.title.setText(data.get(position).getCity());
        holder.description.setText("This is where the description of the forcast will be displayed");

        /**
         * Method for creating automated email intents for weather condition
         */

        //holder.date.setText(data.get(position).);

//        if (weather.place.getCity() == "Kingston") {
//
//            if (weather.currentCondition.getDescription() == "shower rain") {
//
//                String message = weather.place.getCity() + "will be experiencing "
//                        + weather.currentCondition.getDescription() + ". IT personnels do not come out on the road today. Thank you";
//
//                holder.title.setText("Weather Alert Kingston");
//                holder.description.setText(message);
//
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//
//            }
//            if (weather.currentCondition.getDescription() == "clear sky") {
//
//                String message = weather.place.getCity() + "will be experiencing "
//                        + weather.currentCondition.getDescription() + ". Work is the normal eight hours. Thank you.";
//
//                holder.title.setText("Weather Alert Kingston");
//                holder.description.setText(message);
//
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//            }
//            if (weather.currentCondition.getDescription() == "thunderstorm") {
//
//                String message = weather.place.getCity() + "will be experiencing "
//                        + weather.currentCondition.getDescription() + ". Work is four (4) hours. Thank you.";
//
//                holder.title.setText("Weather Alert Kingston");
//                holder.description.setText(message);
//
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//            }
//            if (weather.currentCondition.getDescription() == "rain") {
//
//                String message = weather.place.getCity() + "will be experiencing "
//                        + weather.currentCondition.getDescription() + ". IT personnels do not come out on the road today. Thank you.";
//
//                holder.title.setText("Weather Alert Kingston");
//                holder.description.setText(message);
//
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//            }
//        } else {
//            if (weather.place.getCity() == "Montego Bay") {
//
//                if (weather.currentCondition.getDescription() == "shower rain") {
//
//                    String message = weather.place.getCity() + "will be experiencing "
//                            + weather.currentCondition.getDescription() + ". IT personnels do not come out on the road today. Thank you";
//
//                    holder.title.setText("Weather Alert Montego Bay");
//                    holder.description.setText(message);
//
//                    emailIntent.setData(Uri.parse("mailto:"));
//                    emailIntent.setType("text/plain");
//                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//
//                }
//                if (weather.currentCondition.getDescription() == "clear sky") {
//
//                    String message = weather.place.getCity() + "will be experiencing "
//                            + weather.currentCondition.getDescription() + ". Work is the normal eight hours. Thank you.";
//
//                    holder.title.setText("Weather Alert Montego Bay");
//                    holder.description.setText(message);
//
//                    emailIntent.setData(Uri.parse("mailto:"));
//                    emailIntent.setType("text/plain");
//                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//                }
//                if (weather.currentCondition.getDescription() == "thunderstorm") {
//
//                    String message = weather.place.getCity() + "will be experiencing "
//                            + weather.currentCondition.getDescription() + ". Work is four (4) hours. Thank you.";
//
//                    holder.title.setText("Weather Alert Montego Bay");
//                    holder.description.setText(message);
//
//                    emailIntent.setData(Uri.parse("mailto:"));
//                    emailIntent.setType("text/plain");
//                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//                }
//                if (weather.currentCondition.getDescription() == "rain") {
//
//                    String message = weather.place.getCity() + "will be experiencing "
//                            + weather.currentCondition.getDescription() + ". IT personnels do not come out on the road today. Thank you.";
//
//                    holder.title.setText("Weather Alert Montego Bay");
//                    holder.description.setText(message);
//
//                    emailIntent.setData(Uri.parse("mailto:"));
//                    emailIntent.setType("text/plain");
//                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{dbHelper.getItEmployee("IT")});
//                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Weather Alert");
//                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//                }
//            }
//
//        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
