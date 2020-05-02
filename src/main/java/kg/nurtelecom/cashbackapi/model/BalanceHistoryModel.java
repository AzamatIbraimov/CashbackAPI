package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceHistoryModel {
    private Long clientId;
    private Long bonusId;
    private Long typeId;
    private Long userId;
    private Long orgId;
    private Double amount;
    private Double invoiceAmount;
    private String bill;
    private OperationType operationType;
}
