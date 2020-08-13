package Commands;

import App.Bot;
import Weather.WeatherApi;
import kotlin.Pair;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

public class CommandFactory {
    Set<String> commands = new HashSet<>();
    WeatherApi weatherApi = WeatherApi.getInstance();

    public CommandFactory() {
        commands.add("weather");
        commands.add("погода");
        commands.add("help");
    }

    public boolean contains(String command) {
        return commands.contains(command.toLowerCase());
    }

    public void MessageHandler(Update update, Bot bot) {
        String message = update.getMessage().getText();
        Pair<String, String> pair = CommandParser.getCommand(message);
        if (pair == null)
            return;

        Command command;

        switch (pair.component1()) {
            case "weather":
            case "погода":
                command = new WeatherCommand(pair.component2(), update, bot, weatherApi);
                break;
            case "help":
                command = new HelpCommand(pair.component2(), update, bot);
                break;
            default:
                command = new UnknownCommand("", update, bot);
                break;
        }
        command.run();
    }

}
