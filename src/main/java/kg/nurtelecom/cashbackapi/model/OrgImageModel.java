package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgImageModel {
    private Long id;
    private Organization organization;
    private String image;
}
