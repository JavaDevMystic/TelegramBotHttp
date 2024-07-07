package uz.pdp.g42.bot.service;

import uz.pdp.g42.common.model.User;
import uz.pdp.g42.common.service.FileService;
import uz.pdp.g42.common.service.UserService;

import java.io.IOException;
import java.util.List;

public class SendFileHistorService {

    private static FileService fileService = new FileService();
    private static final UserService userService = new UserService(fileService);
    public  void addUser(User user) throws IOException {
        List<User> list = userService.list();
        boolean b = list.stream().anyMatch(user1 -> user1.getId().equals(user.getId()));
        if (b){
            return;
        }
        userService.add(user);
    }

    public void sendFileWikipidiya(String req){
        String request = req.replace(" ","_");


    }


}
