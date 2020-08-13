package Commands;

import kotlin.Pair;

public class CommandParser {

    public static Pair<String, String> getCommand(String message) {
        message = message.trim();
        if (!message.startsWith("/"))
            return null;

        message = message.replaceFirst("/", "");
        if (!message.contains(" "))
            return new Pair<>(message, "");         //command without any text

        int id = message.indexOf(" ");
        String command = message.substring(0, id);
        String text = message.substring(id + 1);

        return new Pair<>(command, text);
    }
}
