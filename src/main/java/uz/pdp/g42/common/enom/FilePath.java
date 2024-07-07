package uz.pdp.g42.common.enom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum FilePath {
    USER(" "),
    WIKIPIDIYA(" "),
    VIDIO("");
    private String path;
}
