package Model;

/**
 * Created by penai on 28/10/2016.
 */

public class Weather {

    public Place place;
    public byte[] iconData;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Clouds clouds = new Clouds();

}
