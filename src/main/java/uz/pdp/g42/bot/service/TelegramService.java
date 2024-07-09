package uz.pdp.g42.bot.service;//package uz.pdp.g42.bot.service;
//
//import java.io.*;
//import java.nio.file.*;
//import java.util.concurrent.*;
//import org.apache.http.*;
//import org.apache.http.client.methods.*;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.*;
//import org.apache.http.impl.client.*;
//import uz.pdp.g42.common.service.FileService;
//
//public class TelegramService {
//
//    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
//    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN;
//
//    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//    public void handleRequest(FileService fileService, String text, Long chatId) {
//        executorService.submit(() -> {
//            try {
//                Path filePath = fileService.writeToFile(text);
//                sendFileToTelegram(filePath, chatId);
//                Files.delete(filePath); // Delete the file after sending
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//    public void sendFileToTelegram(Path filePath, Long chatId) throws IOException {
//        executorService.submit(() -> {
//            try (CloseableHttpClient client = HttpClients.createDefault()) {
//                HttpPost upload = new HttpPost(BASE_URL + "/sendDocument?chat_id=" + chatId);
//
//                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//                builder.addBinaryBody("document", filePath.toFile(), ContentType.DEFAULT_BINARY, filePath.getFileName().toString());
//                HttpEntity part = builder.build();
//
//                upload.setEntity(part);
//
//                try (CloseableHttpResponse response = client.execute(upload)) {
//                    System.out.println("Response: " + response.getStatusLine());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    public void shutdown() {
//        executorService.shutdown();
//    }
//}

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;
import org.apache.http.impl.client.*;

public class TelegramService {

    private static final String BOT_TOKEN = "6845312626:AAGd22fui6iwKpfnjFfGqYrTSFX4kB3GtdE";
    private static final String BASE_URL = "https://api.telegram.org/bot" + BOT_TOKEN;
    private static final Path FILE_PATH = Paths.get("wiki_info.txt");
    private static final Object FILE_LOCK = new Object();

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);  // Thread pool for handling multiple requests

    public void handleRequest(String text, Long chatId) {
        executorService.submit(() -> {
            try {
                writeToFile(text);
                sendFileToTelegram(FILE_PATH, chatId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void writeToFile(String text) throws IOException {
        synchronized (FILE_LOCK) {
            Files.write(FILE_PATH, text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    public void sendFileToTelegram(Path filePath, Long chatId) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost upload = new HttpPost(BASE_URL + "/sendDocument?chat_id=" + chatId);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("document", filePath.toFile(), ContentType.DEFAULT_BINARY, filePath.getFileName().toString());
            HttpEntity part = builder.build();

            upload.setEntity(part);

            try (CloseableHttpResponse response = client.execute(upload)) {
                System.out.println("Response: " + response.getStatusLine());
            }
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}