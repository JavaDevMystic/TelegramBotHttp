package uz.pdp.g42.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;

public class Main {
    private static final BotMain botMain=BotMain.getInstance();
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
<<<<<<< HEAD
        botsApi.registerBot(new BotMain());
=======
        botsApi.registerBot(botMain);


//        HashMap<String[],Long> hashMap=new HashMap<>();
>>>>>>> 2e4b13e661dc804cc08eeb978faefc6311f232ac

    }
}
