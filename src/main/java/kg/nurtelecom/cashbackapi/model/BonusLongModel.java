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
public class BonusLongModel {
    private Long id;
    private Date validFrom;
    private Date validTo;
    private Integer validity;
    private Date createdDate;
    private String orgBonusType;
    private String orgName;
    private String image;
    private String orgDesc;
    private String desc;
    private Long orgId;
}
