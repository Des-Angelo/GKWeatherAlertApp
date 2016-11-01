package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by penai on 28/10/2016.
 */

public class Utils {

    public static final String BASE_URL_KINGSTON = "http://api.openweathermap.org/data/2.5/weather?id=3489854";
    public static final String BASE_URL_MONTEGO_BAY = "http://api.openweathermap.org/data/2.5/weather?id=3489460";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";
    public static final String APP_ID = "&APPID=ad4640441153fdb92ba45661ce15746a&units=metric";
    public static final String FORECAST_URL_KINGSTON = "http://api.openweathermap.org/data/2.5/forecast/daily?id=3489854";
    public static final String FORECAST_URL_MONTEGO_BAY = "http://api.openweathermap.org/data/2.5/forecast/daily?id=3489460";
    public static final String FORECAST_FIVE_DAYS = "&cnt=5";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{

        JSONObject jObject = jsonObject.getJSONObject(tagName);

        return jObject;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{

        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws Exception{

        return (float) jsonObject.getDouble(tagName);
    }

    public static double getDouble(String tagName, JSONObject jsonObject) throws Exception{

        return (float) jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws Exception{

        return jsonObject.getInt(tagName);
    }

}
