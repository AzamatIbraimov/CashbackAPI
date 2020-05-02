package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.enums.ClientSex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientChangeModel {
    private String firstName;
    private String lastName;
    private String patronymic;
    private ClientSex clientSex;
    private String nationality;
    private String locale;
}
