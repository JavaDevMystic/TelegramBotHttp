package uz.pdp.g42.bot;

import com.google.gson.Gson;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.g42.bot.service.KeyboardMarkapService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
            Long chatId = message.getChatId();
            String txt = message.getText();

            switch (txt) {
                case "/start":
                    sendWelcomeMessage(chatId);

                    break;
                case "history":
                    try {
                        sendInlineKeyboard(chatId);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    sendDefaultMessage(chatId);
                    break;
            }
        } else if (update.hasCallbackQuery()) {
            // Callback queriesni qayta ishlash
        }
    }

    private void sendWelcomeMessage(Long chatId) {
                List<String> list = List.of("video", "Wikipedia izlash", "surat izlash", "history");

//        String text = """
//                Telegram botimizga xush kelibsiz.
//
//                Ushbu bot sizga quyidagilarni taklif qiladi:
//                1️⃣. Ma'lumot izlash
//                2️⃣. Video izlash
//                3️⃣. Surat izlash
//                """;
//

//        try {
//            executeJobReply(chatId, text, keyboardMarkapService.replyKeyboard(list, 2));
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void sendInlineKeyboard(Long chatId) throws URISyntaxException, TelegramApiException {
        String text = "Welcome to Bot";
        List<String> list = List.of("video🎥🎥🎥", "wikipedia izlash📖📖📖", "pull question");
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new URI("https://miro.medium.com/v2/resize:fit:1400/1*lmbFqu5aGrPLdiRIHbe6gQ.jpeg").toString()));
        sendPhoto.setCaption(text);
        sendPhoto.setReplyMarkup(keyboardMarkapService.buildInlineKeyboardMarkup(list,2));
        execute(sendPhoto);
//        try {
//            InlineKeyboardMarkup inlineKeyboard = keyboardMarkapService.buildInlineKeyboardMarkup(list, 2);
//            executeJobInline(chatId, "Menudan birini tanlang", inlineKeyboard);
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void sendDefaultMessage(Long chatId) {
        String text = "Sizning xabaringizni tushunmadim. Iltimos, boshqa buyruq kiriting.";
        try {
            executeJobReply(chatId, text, null);
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
