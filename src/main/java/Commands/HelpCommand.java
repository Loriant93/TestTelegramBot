package Commands;

import App.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends Command{
    public HelpCommand(String text, Update update, Bot bot) {
        super("help", text, update, bot);
    }

    @Override
    public void run() {
        super.run();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        message.setText("I hope this is helpful");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
