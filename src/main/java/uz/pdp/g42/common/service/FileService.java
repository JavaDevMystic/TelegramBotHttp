package uz.pdp.g42.common.service;

import com.google.gson.Gson;
import uz.pdp.g42.common.enom.FilePath;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService<T> {

    private static final Gson gson = new Gson();

    public void writeFile(T t, FilePath filePath, Class<T[]> clazz) throws IOException {
        List<T> list = getList(filePath.getPath(), clazz);
        list.add(t);
        try (FileWriter fileWriter = new FileWriter(filePath.getPath())) {
            fileWriter.write(gson.toJson(list));
        }
    }

    public List<T> readFile(FilePath filePath, Class<T[]> clazz) throws IOException {
        return getList(filePath.getPath(), clazz);
    }

    public List<T> getList(String filePath, Class<T[]> clazz) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] bytes = fis.readAllBytes();
            String json = new String(bytes);
            if (json.isEmpty()) {
                return new ArrayList<>();
            }
            T[] ts = gson.fromJson(json, clazz);
            List<T> list = new ArrayList<>();
            for (T t : ts) {
                list.add(t);
            }
            return list;
        }
    }
}