package App;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class Solution {
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public static void main(String[] args) throws IOException {

        log.info("Starting app");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("Closing app")));
        startBot();
    }

    private static void startBot() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            log.error("Exception in startBot", e);
        }
    }
}
