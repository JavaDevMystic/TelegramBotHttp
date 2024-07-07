package uz.pdp.g42.bot;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import uz.pdp.g42.bot.service.KeyboardMarkapService;
import uz.pdp.g42.common.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BotMain extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
    private static final String USERNAME = "pdphandler_bot";
    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
    private static final String CHAT_ID = "5682972913";
    private static KeyboardMarkapService keyboardMarkapService = new KeyboardMarkapService();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long id=update.getMessage().getChatId();
            User currentUser=getUserById(id);
        }




        Long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();

        if (messageText.equals("/start")) {
            sendWelcomeMessage(chatId);
        } else if (messageText.equals("inline")) {
            sendInlineKeyboard(chatId);
        } else {
            sendDefaultMessage(chatId);
        }
    }

    private void sendWelcomeMessage(Long chatId) {
        String text = "Welcome to Bot";
        List<String> list = List.of("video🎥🎥🎥", "wikipedia izlash📖📖📖", "pull question", "history");

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId.toString());
        sendPhoto.setPhoto(new InputFile("https://miro.medium.com/v2/resize:fit:1400/1*lmbFqu5aGrPLdiRIHbe6gQ.jpeg"));
        sendPhoto.setCaption(text);
//        sendPhoto.setReplyMarkup(keyboardMarkapService.replyKeyboard());


        try {
            executeJobReply(chatId, sendPhoto);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendInlineKeyboard(Long chatId) {
        List<String> list = List.of("video🎥🎥🎥", "wikipedia izlash📖📖📖", "pull question");

        try {
            InlineKeyboardMarkup inlineKeyboard = keyboardMarkapService.buildInlineKeyboardMarkup(list, 2);
            executeJobInline(chatId, "Menudan birini tanlang", inlineKeyboard);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendDefaultMessage(Long chatId) {
        String text = "Sizning xabaringizni tushunmadim. Iltimos, boshqa buyruq kiriting.";
        try {
            executeJobInline(chatId, text, null);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME; // o'zingizning bot username ni kiriting
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN; // o'zingizning bot token ni kiriting
    }

    private void executeJobReply(Long chatId, SendPhoto sendPhoto) throws IOException, InterruptedException {
        Gson gson = new Gson();
        TgReplyMessage message = new TgReplyMessage(chatId.toString(), sendPhoto);

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

    // TgReplyMessage and TgInlineMessage classes should be implemented accordingly
}
