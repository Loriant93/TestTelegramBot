package Weather;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class WeatherApi {
    private final static Logger logger = LoggerFactory.getLogger(WeatherApi.class);
    private final OkHttpClient client = new OkHttpClient();
    private String ApiKey;
    private static WeatherApi weatherApi;

    private WeatherApi() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("BotSetting.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            ApiKey = prop.getProperty("WeatherApiKey");
        } catch (IOException e) {
            logger.error("error", e);
        }
    }

    public static WeatherApi getInstance() {
        if (weatherApi == null)
            weatherApi = new WeatherApi();
        return weatherApi;
    }

    public String getWeather(String city) throws IOException {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&lang=ru&units=metric", city, ApiKey);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            JSONObject jsonObject = new JSONObject(response.body().string());
            response.close();
            return parsingWeatherJson(jsonObject);
        } else {
            logger.info("Ошибка в getWeather.Response - code :{} message :{}, city - {}", response.code(), response.message(), city);
            return "Ошибка. Попробуйте ввести другое название города.";
        }
    }

    private String parsingWeatherJson(JSONObject object) {

        String city = object.get("name").toString();
        JSONObject main = object.getJSONObject("main");
        String humidity = main.get("humidity").toString();
        String temp = main.get("temp").toString();
        String tempFeels = main.get("feels_like").toString();
        JSONObject weather = new JSONObject(new JSONArray(object.get("weather").toString()).get(0).toString()); //TODO придумать как лучше достать description.
        String description = weather.get("description").toString();
        String windSpeed = object.getJSONObject("wind").get("speed").toString();
        return String.format("Город - %s , погода - %s, температура - %s, ощущается - %s, влажность - %s, ветер - %s м/с",
                city, description, temp, tempFeels, humidity, windSpeed);
    }
}
