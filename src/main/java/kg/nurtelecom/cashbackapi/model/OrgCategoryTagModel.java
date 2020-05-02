package kg.nurtelecom.cashbackapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgCategoryTagModel {

    public OrgCategoryTagModel (String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Override
    public String toString() {
        return name + " " + id;
    }
}
