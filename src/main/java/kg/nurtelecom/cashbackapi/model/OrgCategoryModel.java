package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgCategoryModel {
    private Long id;
    private String image;
    @NotBlank
    @Size(min = 5,max = 255)
    private String name;
    @Size(min = 50, max = 255)
    private String description;
}