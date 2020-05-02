package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonusValueModel {
    private Long bonusId;
    private String name;
    private Integer min;
    private Integer max;
    private Integer value;
}
