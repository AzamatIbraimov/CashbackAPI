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
public class FilialRateModel {
    private Long id;
    private String comment;
    private Double rate;
    private Date created_date;
    private String clientFirstName;
    private String clientLastName;
}
