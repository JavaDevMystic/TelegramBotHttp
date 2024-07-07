package uz.pdp.g42.common.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.pdp.g42.common.enom.FilePath;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService<T> {

    private static final Gson gson = new Gson();

    public List<T> getList(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        Type listType = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    public void writeFile(T t, FilePath filePath) throws IOException {
        List<T> list = getList(filePath.getPath());
        list.add(t);
        try (FileWriter fileWriter = new FileWriter(filePath.getPath())) {
            fileWriter.write(gson.toJson(list));
        }
    }

    public List<T> readFile(FilePath filePath) throws IOException {
        return getList(filePath.getPath());
    }
}
