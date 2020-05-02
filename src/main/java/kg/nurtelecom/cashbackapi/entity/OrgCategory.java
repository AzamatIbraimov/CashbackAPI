package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Organization Category
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "org_category")
public class OrgCategory {

    /**
     * Tags
     */
    @ManyToMany
    @JoinTable(
            name = "org_category_tag",
            joinColumns = @JoinColumn(name = "org_category_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Set<OrgCategoryTag> tags;
    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "org_category_seq", sequenceName = "org_category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_category_seq")
    private Long id;
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
     * Image URL
     */
    @Column(name = "image")
    private String image;

    /**
     * Created Date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date createdDate;

}
