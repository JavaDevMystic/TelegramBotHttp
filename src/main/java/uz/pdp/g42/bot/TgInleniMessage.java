package uz.pdp.g42.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@AllArgsConstructor
@Getter
@Setter
public class TgInleniMessage {
    final String chat_id;
    final String text;
    final InlineKeyboardMarkup inline_Keyboard;
}
