package Weather;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WeatherApi {
    private final OkHttpClient client = new OkHttpClient();
    private String ApiKey;


    public WeatherApi() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("BotSetting.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            ApiKey = prop.getProperty("WeatherApiKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWeather(String city) throws IOException {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&lang=ru&units=metric", city, ApiKey);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        System.out.println(jsonObject.toString());
        return "Json";
    }
}
