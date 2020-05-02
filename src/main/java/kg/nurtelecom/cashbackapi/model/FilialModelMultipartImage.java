package kg.nurtelecom.cashbackapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilialModelMultipartImage {
    private Long id;
    private MultipartFile image;
    private Boolean status;
    @Size(max = 255)
    @NotNull
    private String address;
    @Size(min = 5, max = 255)
    private String name;
    @Size(min = 20, max = 255)
    private String description;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    private Long orgId;
    private Double averageRate;
}
