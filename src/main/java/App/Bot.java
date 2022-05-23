package App;

import Commands.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private final Properties prop = new Properties();
    private final CommandFactory factory = new CommandFactory();

    public Bot() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("BotSetting.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            logger.error("error reading properties", e);
        }
    }

    public void onUpdateReceived(Update update) {
        //String message = update.getMessage().getText();
        //sendMsg(update.getMessage().getChatId().toString(), message);
        factory.MessageHandler(update, this);
    }

    @Override
    public String getBotToken() {
        return prop.getProperty("BotToken");
    }

    public String getBotUsername() {
        return prop.getProperty("BotName");
    }
}
