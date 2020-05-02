package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Organization-selectable preference chosen by organization
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "org_preference_category")
public class OrgPreferenceCategory {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "org_preference_category_seq", sequenceName = "org_preference_category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_preference_category_seq")
    private Long id;

    /**
     * Organization
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Preference Category
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private PreferenceCategory preferenceCategory;
}
