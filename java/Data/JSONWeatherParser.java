package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Place;
import Model.Weather;
import Util.Utils;

import static Util.Utils.getFloat;
import static Util.Utils.getInt;
import static Util.Utils.getString;

/**
 * Created by penai on 28/10/2016.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data) {

        Weather weather = new Weather();
        // Create JsonObject from data

        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();

            JSONObject coordObj = Utils.getObject("coord", jsonObject); // Holds coordinates data from api
            place.setLatitude(Utils.getFloat("lat", coordObj));
            place.setLongitude(Utils.getFloat("lon", coordObj));

            // Get the sys object
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(getString("country", sysObj));
            place.setLastUpdated(getInt("dt", jsonObject));
            place.setCity(getString("name", jsonObject));
            weather.place = place;

            // Get the weather information
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(getInt("id", jsonWeather));
            weather.currentCondition.setDescription(getString("description", jsonWeather));
            weather.currentCondition.setCondition(getString("main", jsonWeather));
            weather.currentCondition.setIcon(getString("icon", jsonWeather));

            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            weather.wind.setDeg(Utils.getFloat("deg", windObj));

            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(getInt("all", cloudObj));

            JSONObject tempObj = Utils.getObject("main", jsonObject);
            weather.currentCondition.setHumidity(getInt("humidity", tempObj));
            weather.currentCondition.setPressure(getInt("pressure", tempObj));
            weather.currentCondition.setTemperature(Utils.getDouble("temp", tempObj));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min", tempObj));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max", tempObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
