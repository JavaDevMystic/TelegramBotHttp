//package uz.pdp.g42.bot;
//
//import com.google.gson.Gson;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import uz.pdp.g42.bot.service.KeyboardMarkapService;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BotMain extends TelegramLongPollingBot {
//
//    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
//    private static final String USERNAME = "pdphandler_bot";
//    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
//    private static final String CHAT_ID = "5682972913";
//
//    private static final KeyboardMarkapService keyboardMarkapService = new KeyboardMarkapService();
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            Message message = update.getMessage();
//            String txt = message.getText();
//
//            switch (txt) {
//                case "/start" -> sendWelcomeMessage();
//                case "history" -> sendInlineKeyboard();
//                default -> sendDefaultMessage();
//            }
//        } else if (update.hasCallbackQuery()) {
//            // Handle callback queries here
//        }
//    }
//
//    private void sendWelcomeMessage() {
//        String text = """
//                Welcome to our Telegram Bot.
//
//                This bot is for you.
//                1️⃣. To search for information
//                2️⃣. To search for video
//                3️⃣. Image search
//                Will help.
//                """;
//
//        List<String> list = List.of("video", "search Wikipedia", "search picture", "history");
//        try {
//            executeJobReply(Long.parseLong(CHAT_ID), text, keyboardMarkapService.replyKeyboard(list, 2));
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void sendInlineKeyboard() {
//        List<String> list = List.of("video🎥🎥🎥", "search Wikipedia📖📖📖", "search picture 🎴🎴🎴");
//        try {
//            executeJobInline(Long.parseLong(CHAT_ID), "Choose one from the menu", keyboardMarkapService.buildInlineKeyboardMarkup(list, 2));
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void sendDefaultMessage() {
//        // Handle default case if needed
//    }
//
//    @Override
//    public String getBotUsername() {
//        return USERNAME;
//    }
//
//    @Override
//    public String getBotToken() {
//        return BOT_TOKEN;
//    }
//
//    private void executeJobReply(Long chatId, String text, ReplyKeyboardMarkup replyKeyboard) throws IOException, InterruptedException {
//        Gson gson = new Gson();
//        TgReplyMessage message = new TgReplyMessage(chatId.toString(), text, replyKeyboard);
//
//        String json = gson.toJson(message);
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(BASE_URL))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(json))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("Response: " + response.body());
//    }
//
//    private void executeJobInline(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) throws IOException, InterruptedException {
//        Gson gson = new Gson();
//        TgInleniMessage message = new TgInleniMessage(chatId.toString(), text, inlineKeyboardMarkup);
//
//        String json = gson.toJson(message);
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(BASE_URL))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(json))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("Response: " + response.body());
//    }
//}

package uz.pdp.g42.bot;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.pdp.g42.bot.service.KeyboardMarkapService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class BotMain extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
    private static final String USERNAME = "pdphandler_bot";
    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
    private static final String CHAT_ID = "5682972913";

    private static final KeyboardMarkapService keyboardMarkapService = new KeyboardMarkapService();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = update.getMessage().getChatId();
            System.out.println(chatId);

            String txt = message.getText();

            switch (txt) {
                case "/start" -> sendWelcomeMessage();
                case "history" -> sendInlineKeyboard();
                default -> sendDefaultMessage();
            }
        } else if (update.hasCallbackQuery()) {
            // Handle callback queries here
        }
    }

    private void sendWelcomeMessage() {
        String text = """
                Welcome to our Telegram Bot.
                                
                This bot is for you.
                1️⃣. To search for information
                2️⃣. To search for video
                3️⃣. Image search
                Will help.
                """;

        List<String> list = List.of("video", "search Wikipedia", "search picture", "history");
        try {
            executeJobReply(Long.parseLong(CHAT_ID), text, keyboardMarkapService.replyKeyboard(list, 2));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineKeyboard() {
        List<String> list = List.of("video🎥🎥🎥", "search Wikipedia📖📖📖", "search picture 🎴🎴🎴");
        try {
            InlineKeyboardMarkup inlineKeyboard = keyboardMarkapService.buildInlineKeyboardMarkup(list, 2);
            executeJobInline(Long.parseLong(CHAT_ID), "Choose one from the menu", inlineKeyboard);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendDefaultMessage() {
        // Handle default case if needed
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void executeJobReply(Long chatId, String text, ReplyKeyboardMarkup replyKeyboard) throws IOException, InterruptedException {
        Gson gson = new Gson();
        TgReplyMessage message = new TgReplyMessage(chatId.toString(), text, replyKeyboard);

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

    private void executeJobInline(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) throws IOException, InterruptedException {
        Gson gson = new Gson();
        TgInleniMessage message = new TgInleniMessage(chatId.toString(), text, inlineKeyboardMarkup);

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
}
