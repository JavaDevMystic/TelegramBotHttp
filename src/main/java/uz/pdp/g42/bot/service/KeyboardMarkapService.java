package uz.pdp.g42.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardMarkapService {


    public  ReplyKeyboardMarkup replyKeyboard(List<String> menus, int col) {
        ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        replyKeyboard.setResizeKeyboard(true);
        replyKeyboard.setSelective(true);
        replyKeyboard.setOneTimeKeyboard(true);
        replyKeyboard.setKeyboard(rows);

        KeyboardRow row = new KeyboardRow();
        for (int i = 1; i <= menus.size(); i++) {
            row.add(new KeyboardButton(menus.get(i - 1)));
            if (i % col == 0) {
                rows.add(row);
                row = new KeyboardRow();
            }
        }

        if (!row.isEmpty()) {
            rows.add(row);
        }

        return replyKeyboard;
    }

    public InlineKeyboardMarkup buildInlineKeyboardMarkup(List<String> list, int col) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(rows);

        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(list.get(i));
            inlineKeyboardButton.setCallbackData(list.get(i).toString());

            row.add(inlineKeyboardButton);

            if ((i + 1) % col == 0) {
                rows.add(new ArrayList<>(row));
                row.clear();
            }
        }

        if (!row.isEmpty()) {
            rows.add(new ArrayList<>(row));
        }

        return inlineKeyboardMarkup;
    }



}
