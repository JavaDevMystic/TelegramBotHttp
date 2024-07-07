package uz.pdp.g42.bot.service;

import uz.pdp.g42.common.model.User;
import uz.pdp.g42.common.service.UserService;

import java.io.IOException;
import java.util.List;

public class SendFileHistorService {

    private static final UserService userService = new UserService();
    public  void addUser(User user) throws IOException {
        List<User> list = userService.list();
        boolean b = list.stream().anyMatch(user1 -> user1.getId().equals(user.getId()));
        if (!b){
            return;
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        userService.add(newUser);
    }

    public void sendFileWikipidiya(String req){
        String request = req.replace(" ","_");

    }


}
