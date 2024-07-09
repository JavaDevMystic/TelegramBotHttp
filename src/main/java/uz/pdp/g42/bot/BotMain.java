package uz.pdp.g42.bot;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.g42.bot.database.Database;
import uz.pdp.g42.bot.service.HTTPService;
import uz.pdp.g42.bot.service.KeyboardMarkapService;
import uz.pdp.g42.bot.service.Status;
import uz.pdp.g42.common.model.User;
import uz.pdp.g42.common.service.FileService;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class BotMain extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
    private static final String USERNAME = "pdphandler_bot";
    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
    private static final String CHAT_ID = "5682972913";
    private static FileService fileService = new FileService();
    private static Database database = new Database();
    private static KeyboardMarkapService keyboardMarkapService = new KeyboardMarkapService();
    private static Gson gson = new Gson();
    private static HTTPService httpService = new HTTPService();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            User user = getUserById(chatId);
            String text = update.getMessage().getText();
            switch (text){
                case "/start" -> {
                    if (update.hasMessage()) {
                        String message1 = "Welcome to Bot";
                        String url = "https://res.cloudinary.com/jerrick/image/upload/v1678878952/6411a8e808ea69001e41acaa.jpg";
                        sendTextAndPhoto(chatId,message1,url);

                        String message2 = """
                                Our bot is for you
                                1️⃣ -> Information search.
                                2️⃣-> Search video
                                3️⃣ -> Image search
                                It helps in such cases.
                                You can use one of the menus.
                                """;
                        sendBotTextAndReplyMarkup(chatId,message2,Status.ALL_ONE);


                    }
                        break;
                }
                case "WIKIPEDIA📚📚📚" -> {

                        String message = "Enter the information you want to search for🔎🔎🔎";
                        sendBotText(chatId,message);
                        String request = update.getMessage().getText();
                        sendBotText(chatId,httpService.searchHttpRequest(request));

                    break;

                }
                case "IMAGE📷📷📷" ->{
                    String message = "Enter the name of the image you are looking for 🔎🔎🔎";
                    sendBotText(chatId,message);
                    String request = update.getMessage().getText();
                   
//                    sendTextAndPhoto(chatId,request,s);
                        sendTextAndPhoto(chatId,request,"https://unsplash.com/s/photos/Iphone-13-pro");

                }
            }
        }
    }



    private void handleContactUpdate(Update update) throws IOException, TelegramApiException {

    }






    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    //// Databases bilan ishlaydi.
    private User getUserById(Long id) {
        for (User user : database.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        User currentUser = new User();
        currentUser.setId(id);
        database.users.add(currentUser);
        return currentUser;
    }

    private void sendBotText(Long id, String message) {
        String chatId = id.toString();
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
            HttpClient httpClient = HttpClient.newHttpClient();
            String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage?chat_id=" + chatId + "&text=" + encodedMessage;
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendBotTextAndReplyMarkup(Long chatId, String message, String[][] buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardMarkapService.buttonMaking(buttons);
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

            TgReplyMessage sendMessage = new TgReplyMessage(chatId, message, replyKeyboardMarkup);

            String json = gson.toJson(sendMessage);

            HttpClient httpClient = HttpClient.newHttpClient();
            String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    //// Botga text bilan birga rasim ham jo'natadi
    private void sendTextAndPhoto(Long id, String text, String photoUrl) {
        String chatId = id.toString();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://api.telegram.org/bot" + BOT_TOKEN + "/sendPhoto");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("chat_id", chatId, ContentType.TEXT_PLAIN);
            builder.addTextBody("caption", text, ContentType.TEXT_PLAIN);

            URL url = new URL(photoUrl);
            builder.addBinaryBody(
                    "photo",
                    url.openStream(),
                    ContentType.APPLICATION_OCTET_STREAM,
                    "photo.jpg"
            );

            HttpEntity multipart = builder.build();
            request.setEntity(multipart);

            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity responseEntity = response.getEntity();
                // Handle response if needed
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



    ////////////////////////////////////////////////////////

//    public void sendFile(Long chatId) {
//        String data = httpService.searchHttpRequests(request); // HTTP xizmatidan ma'lumot olish
//        FileService fileService = new FileService();
//        File file = fileService.createTxtFile(data, chatId); // Ma'lumotni noyob txt faylga saqlash
//
//        SendDocument sendDocumentRequest = new SendDocument();
//        sendDocumentRequest.setChatId(chatId.toString());
//        InputFile inputFile = new InputFile(file);
//        sendDocumentRequest.setDocument(inputFile);
//
//        try {
//            execute(sendDocumentRequest);
//            file.delete(); // Fayl jo'natilgandan keyin uni o'chirish
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }





