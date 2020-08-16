package Commands;

import App.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UnknownCommand extends Command {
    public UnknownCommand(String text, Update update, Bot bot) {
        super("unknown", text, update, bot);
    }

    @Override
    public void run() {
        super.run();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        message.setText("unknown command");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            logger.error("error", e);
        }
    }
}
