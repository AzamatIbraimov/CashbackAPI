package kg.nurtelecom.cashbackapi.model;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String phone;
    private String password;
}
