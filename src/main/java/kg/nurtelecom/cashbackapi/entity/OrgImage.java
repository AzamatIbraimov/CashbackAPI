package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "org_image")
public class OrgImage {
    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "org_image_seq", sequenceName = "org_image_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_image_seq")
    private Long id;


    /**
     * Image Organization
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    /**
     * Image Encode To Base64
     */
    @Column(name = "image", nullable = false)
    private String image;
}
