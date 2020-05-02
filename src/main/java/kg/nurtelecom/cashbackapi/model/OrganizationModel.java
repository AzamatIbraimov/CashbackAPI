package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrganizationModel {
    private Long id;
    private String image;
    private Boolean status;
    @Size(min = 5, max = 255)
    private String name;
    private Long categoryId;
    private String categoryName;
    @Size(min = 50, max = 255)
    private String description;
}