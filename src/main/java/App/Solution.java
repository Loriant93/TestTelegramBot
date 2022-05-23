package App;

import org.slf4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static org.slf4j.LoggerFactory.getLogger;

public class Solution {
    private static final Logger log = getLogger(Solution.class);

    public static void main(String[] args) {
        log.info("Starting app");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("Closing app")));
        startBot();
    }

    private static void startBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            log.error("Exception in startBot", e);
        }
    }
}
