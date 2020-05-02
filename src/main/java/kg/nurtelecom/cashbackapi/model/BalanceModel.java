package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceModel {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String image;
    private Long balanceId;
    private Double amount;
    private String type;
}
