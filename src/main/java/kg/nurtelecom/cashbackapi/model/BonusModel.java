package kg.nurtelecom.cashbackapi.model;


import kg.nurtelecom.cashbackapi.entity.OrgBonusType;
import kg.nurtelecom.cashbackapi.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonusModel {
    private Long id;
    private OrgBonusType orgBonusType;
    private Organization organization;
    private Boolean status;
    private Date validFrom;
    private Date validTo;
    private Integer validity;
    private Date createdDate;
}
