package uz.pdp.g42.common.enom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum FilePath {
    USER("D:\\Java G42\\4 - Modil\\TelegramBotSendMessageOrRespos\\src\\main\\java\\uz\\pdp\\g42\\json\\user.json"),
    WIKIPIDIYA("D:\\Java G42\\4 - Modil\\TelegramBotSendMessageOrRespos\\src\\main\\java\\uz\\pdp\\g42\\json\\wikipidiya.json"),
    WIKIPIDIYAHISTORY("D:\\Java G42\\4 - Modil\\TelegramBotSendMessageOrRespos\\src\\main\\java\\uz\\pdp\\g42\\json\\wikipidiyahistory.json"),
    VIDIO("");
    private String path;
}
