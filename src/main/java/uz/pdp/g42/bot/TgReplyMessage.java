package uz.pdp.g42.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
@AllArgsConstructor
@Setter
@Getter
public class TgReplyMessage {
    private String chat_id;
    private String text;
    private ReplyKeyboardMarkup reply_markup;

}