package uz.pdp.g42.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Wikipidiya {
    private Long chatId;
    private HashMap<Long,String> hashMap;
}
