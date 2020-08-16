package Commands;

import App.Bot;
import App.Solution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command implements Runnable {
    public final Logger logger = LoggerFactory.getLogger(Command.class);
    public String name;
    public String text;
    public Update update;
    public Bot bot;

    public Command(String name, String text, Update update, Bot bot) {
        this.name = name;
        this.text = text;
        this.update = update;
        this.bot = bot;
    }

    @Override
    public void run() {
        logger.info("Staring new command : " + name + " " + text);
    }
}
