package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventModelMultipartImage {
    private Long id;
    private MultipartFile image;
    private Organization organization;
    private String name;
    private Date dateFrom;
    private Date dateTo;
    private String description;
}
