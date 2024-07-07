package uz.pdp.g42.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardMarkapService {


    public  ReplyKeyboardMarkup replyKeyboard(String[][] buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        List<KeyboardRow> keyboardRows=new ArrayList<>();
        for (String[] button : buttons) {
            KeyboardRow keyboardRow=new KeyboardRow();
            for (String s : button) {
                KeyboardButton keyboardButton=new KeyboardButton(s);
                keyboardRow.add(keyboardButton);
            }
            keyboardRows.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
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
