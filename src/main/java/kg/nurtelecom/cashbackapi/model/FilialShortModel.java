package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilialShortModel {

    private Long id;
    private String address;
    private String name;
    private String description;
    private Double averageRate;

}
