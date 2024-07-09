package uz.pdp.g42.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Setter
@Getter
@AllArgsConstructor
public class TgReplyMessage {
    private final String chat_id;
    private final String text;
    private final ReplyKeyboardMarkup reply_markup;

    public TgReplyMessage(Long chatId, String text, ReplyKeyboardMarkup replyMarkup) {
        this.chat_id = String.valueOf(chatId);
        this.text = text;
        this.reply_markup = replyMarkup;
    }
}
