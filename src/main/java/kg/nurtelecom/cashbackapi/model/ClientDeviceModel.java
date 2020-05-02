package kg.nurtelecom.cashbackapi.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDeviceModel {
    private Long clientId;
    private String phoneNumber;
    private String password;
    private String imei;
}
