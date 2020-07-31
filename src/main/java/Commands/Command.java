package Commands;

import App.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command implements Runnable {
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
        System.out.println("Staring new command : " + name + " " + text);
    }
}
