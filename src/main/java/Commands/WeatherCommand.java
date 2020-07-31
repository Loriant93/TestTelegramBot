package Commands;

import App.Bot;
import Weather.WeatherApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class WeatherCommand extends Command {
    public WeatherCommand(String text, Update update, Bot bot) {
        super("weather", text, update, bot);
    }

    @Override
    public void run() {
        super.run();
        WeatherApi weatherApi = new WeatherApi();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        try {
            String weatherText = weatherApi.getWeather(text);
            message.setText("Погода, которую осталось пропарсить.");
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Ошибка");
        }
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
