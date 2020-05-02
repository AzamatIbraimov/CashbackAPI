package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceConfirmModel {
    private Long clientId;
    private Long typeId;
    private Double invoiceAmount;
    private Double point;
    private String numberCheck;

    @Override
    public String toString() {
        return "BalanceConfirm{" +
                "clientId=" + clientId +
                ", typeId=" + typeId +
                ", invoiceAmount=" + invoiceAmount +
                ", point=" + point +
                ", numberCheck='" + numberCheck + '\'' +
                '}';
    }
}
