package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceHistoryLongModel {
    private Double amount;
    private String date;
    private Double invoiceAmount;
    private String firstName;
    private String lastName;
    private String operationType;
}
