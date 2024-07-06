package uz.pdp.g42.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String userName;
    private String Name;
    private long chatId;
    private String phoneNumber;
}
