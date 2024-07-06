package uz.pdp.g42.bot;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class BotMain extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
    private static final String USERNAME = "https://t.me/pdphandler_bot";
    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
    private static final String CHAT_ID = "5682972913";

    @Override
    public void onUpdateReceived(Update update) {

        String text = """
                Welcome to our Telegram Bot😄😄.
                
                This bot is for you.
                1️⃣. To search for information
                2️⃣. To search for video
                3️⃣. Image search
                Will help.
                """;
        List<String> list = new ArrayList<>();
        list.add("video");
        list.add("search Wikipedia");
        list.add("search picture");
        list.add("history");

        BotMain botService = new BotMain();
        try {
            botService.executeJob(Long.valueOf(CHAT_ID), text, replyKeyboard(list, 2));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void executeJob(Long chatId, String text, ReplyKeyboardMarkup replyKeyboard) throws IOException, InterruptedException {
        Gson gson = new Gson();
        TGMessage message = new TGMessage(chatId.toString(), text, replyKeyboard);

        String json = gson.toJson(message);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());
    }

    private static ReplyKeyboardMarkup replyKeyboard(List<String> menus, int col) {
        ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
//        replyKeyboard.setResizeKeyboard(true);
        replyKeyboard.setResizeKeyboard(false);
        replyKeyboard.setSelective(true);
        replyKeyboard.setOneTimeKeyboard(true);
        replyKeyboard.setKeyboard(rows);

        KeyboardRow row = new KeyboardRow();
        for (int i = 1; i <= menus.size(); i++) {
            row.add(new KeyboardButton(menus.get(i - 1)));
            if (i % col == 0) {
                rows.add(row);
                row = new KeyboardRow();
            }
        }

        if (!row.isEmpty()) {
            rows.add(row);
        }

        return replyKeyboard;
    }

    public class TGMessage {
        private String chat_id;
        private String text;
        private ReplyKeyboardMarkup reply_markup;

        public TGMessage(String chat_id, String text, ReplyKeyboardMarkup reply_markup) {
            this.chat_id = chat_id;
            this.text = text;
            this.reply_markup = reply_markup;
        }

        public String getChatId() {
            return chat_id;
        }

        public void setChatId(String chat_id) {
            this.chat_id = chat_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public ReplyKeyboardMarkup getReplyMarkup() {
            return reply_markup;
        }

        public void setReplyMarkup(ReplyKeyboardMarkup reply_markup) {
            this.reply_markup = reply_markup;
        }
    }
}
