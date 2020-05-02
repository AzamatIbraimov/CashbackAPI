package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Organization
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {

    /**
     * Organization Clients
     */
    @ManyToMany(mappedBy = "organizations")
    Set<Client> clients;

    /**
     * Organization Images
     */
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<OrgImage> images;

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "organization_seq", sequenceName = "organization_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_seq")
    private Long id;
    /**
     * Organization Category
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private OrgCategory orgCategory;
    /**
     * Name
     */
    @Column(name = "name")
    private String name;

    /**
     * Description
     */
    @Column(name = "description")
    private String description;

    /**
     * Name
     */
    @Column(name = "image")
    private String image;


    /**
     * Status
     * active = true / false
     */
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;

    /**
     * Created date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
}