package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Client Preference Value
 * Values that was set by organization according Client preference
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_preference_value")
public class ClientPreferenceValue {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "client_pref_value_seq", sequenceName = "client_pref_value_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_pref_value_seq")
    private Long id;

    /**
     * Client
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Organization
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Organization preference category
     * Preferences chosen by organization
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_preference_category_id")
    private OrgPreferenceCategory organizationPreferenceCategory;

    /**
     * Value
     */
    @Column(name = "value")
    private Integer value;
}