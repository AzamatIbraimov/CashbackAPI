package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerListDto {
    private Long id;
    private String username;
    private Long organizationId;
    private Date createdDate;
    private String roleNameShort;
}
