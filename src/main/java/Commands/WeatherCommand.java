package Commands;

import App.Bot;
import Weather.WeatherApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WeatherCommand extends Command {
    private final WeatherApi weatherApi;

    public WeatherCommand(String text, Update update, Bot bot, WeatherApi weatherApi) {
        super("weather", text, update, bot);
        this.weatherApi = weatherApi;
    }

    @Override
    public void run() {
        super.run();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId().toString());
        try {
            String weatherText = weatherApi.getWeather(text);
            message.setText(weatherText);
        } catch (Exception e) {
            logger.error("error", e);
            message.setText("Ошибка");
        }
        try {
            bot.execute(message);

        } catch (TelegramApiException e) {
            logger.error("error", e);
        }

    }
}
