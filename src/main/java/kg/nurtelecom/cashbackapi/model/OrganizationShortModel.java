package kg.nurtelecom.cashbackapi.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrganizationShortModel {
    private Long id;
    private String image;
    private String name;
    private String categoryName;
}
