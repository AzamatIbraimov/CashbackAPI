package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.enums.BonusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgBonusTypeModel {
    private Long id;
    private String name;
    private BonusType valueType;
    private String description;
}
