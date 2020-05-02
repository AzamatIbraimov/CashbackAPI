package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.enums.ClientSex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientLongModel {

    @NotNull
    private Long id;

    private String image;

    @NotNull
    @Size(min = 5, max = 255)
    private String personalCode;

    @NotNull
    @Size(min = 5, max = 255)
    private String firstName;

    @NotNull
    @Size(min = 5, max = 255)
    private String lastName;

    @NotNull
    @Size(min = 5, max = 255)
    private String patronymic;

    @NotNull
    private ClientSex clientSex;

    private Date createdDate;

    @NotNull
    private String nationality;

    @NotNull
    private String locale;
}
