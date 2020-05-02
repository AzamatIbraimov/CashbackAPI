package kg.nurtelecom.cashbackapi.model;

//<<<<<<< Updated upstream
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.Set;
//=======
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.web.multipart.MultipartFile;
//import javax.validation.constraints.Size;
//>>>>>>> Stashed changes


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrganizationModelMultipartImage {
    private Long id;
    private MultipartFile image;
    private Set<MultipartFile> images;
    private Boolean status;
    @Size(min = 5, max = 255)
    private String name;
    private Long categoryId;
    private String categoryName;
    @Size(min = 50, max = 255)
    private String description;
}
