package kg.nurtelecom.cashbackapi.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDeviceChangeModel {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
