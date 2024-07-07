package uz.pdp.g42.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@AllArgsConstructor
@Getter
@Setter
public class TgInleniMessage {
    private String chat_id;
    private String txt;
    private InlineKeyboardMarkup in_keyboardMarkap;
}
