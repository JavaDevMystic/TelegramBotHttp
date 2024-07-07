package uz.pdp.g42.common.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.objects.Contact;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @EqualsAndHashCode.Include
    private Long id;
    private String Name;
    private Contact contact;
    private String state;
}
