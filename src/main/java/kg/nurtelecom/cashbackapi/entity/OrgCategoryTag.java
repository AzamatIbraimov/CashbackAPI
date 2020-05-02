package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Organization category tag
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class OrgCategoryTag {

    @ManyToMany(mappedBy = "tags")
    Set<OrgCategory> orgCategories;
    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "category_tag_seq", sequenceName = "category_tag_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_tag_seq")
    private Long id;
    /**
     * Name
     */
    @Column(name = "name")
    private String name;

    @PreRemove
    private void removeTagsFromOrgCategories() {
        for (OrgCategory category : orgCategories) {
            category.getTags().remove(this);
        }
    }
}
