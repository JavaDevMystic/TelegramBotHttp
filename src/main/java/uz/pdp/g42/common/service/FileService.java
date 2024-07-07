package uz.pdp.g42.common.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.g42.common.enom.FilePath;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService<T> {

    private static Gson gson = new Gson();
    public  void writeFile(T t,FilePath filePath,Class<T[]> clazz ) throws IOException {
        List<T> list = getList(filePath.getPath(),clazz);
        list.add(t);
        try (
                FileWriter fileWriter = new FileWriter(filePath.getPath())
        ) {
            fileWriter.write(gson.toJson(list));
        }


    }
    public List<T> readFile(FilePath filePath,Class<T[]> clazz) throws IOException {
        return getList(filePath.getPath(),clazz);
    }
    public List<T> getList(String filePath, Class<T[]> clazz) throws IOException {

        try (
                FileInputStream fis = new FileInputStream(filePath)
        ) {
            byte[] bytes = fis.readAllBytes();
            String json = new String(bytes);
            T[] ts = gson.fromJson(json,clazz);
            List<T> list = new ArrayList<T>();
            for (int i=0; i<ts.length ;i++){
                list.add(ts[i]);
            }
            return list;
        }

    }


}
