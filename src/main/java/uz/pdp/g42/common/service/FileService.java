package uz.pdp.g42.common.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.pdp.g42.common.enom.FilePath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileService<T> {

    private static final Gson gson = new Gson();

    public List<T> getList(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        Type listType = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    public void writeFile(T t, String filePath) throws IOException {
        List<T> users = getList(filePath);
        users.add(t);
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
        }
    }

    public List<T> readFile(String filePath) throws IOException {
        return getList(filePath);
    }

    ////////////////////////////////////////////////
    private static final Object FILE_LOCK = new Object();

    public Path writeToFile(String text) throws IOException {
        synchronized (FILE_LOCK) {
            Path filePath = Paths.get("wiki_info_" + UUID.randomUUID() + ".txt");
            Files.write(filePath, text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return filePath;
        }
    }
}
