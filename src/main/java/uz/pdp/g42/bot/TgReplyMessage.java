package uz.pdp.g42.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@RequiredArgsConstructor
@Setter
@Getter
public class TgReplyMessage {
    private final String chat_id;
    private final String photo;
    private final String caption;

    public TgReplyMessage(String chat_id, SendPhoto sendPhoto) {
        this.chat_id = chat_id;
        this.photo = sendPhoto.getPhoto().getAttachName();
        this.caption = sendPhoto.getCaption();
    }
}
