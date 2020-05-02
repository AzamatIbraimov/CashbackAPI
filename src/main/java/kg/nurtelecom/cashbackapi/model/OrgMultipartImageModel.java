package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgMultipartImageModel {
    private Long id;
    private Organization organization;
    private MultipartFile image;
}
