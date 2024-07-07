package uz.pdp.g42.bot.database;

import uz.pdp.g42.common.model.User;

import java.util.HashSet;

public class Database {

    public HashSet<User> users=new HashSet<>();


    private static Database database;
    public static Database getInstance() {
        if (database==null) {
            database=new Database();
        }
        return database;
    }
}
