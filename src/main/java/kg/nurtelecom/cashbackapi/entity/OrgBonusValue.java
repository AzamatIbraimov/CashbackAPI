package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Organization Bonus Value
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "org_bonus_value")
public class OrgBonusValue {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "org_bonus_value_seq", sequenceName = "org_bonus_value_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_bonus_value_seq")
    private Long id;

    /**
     * Organization Bonus
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "org_bonus_id", referencedColumnName = "id")
    private OrgBonus orgBonus;

    /**
     * Minimal threshold
     */
    @Column(name = "min")
    private Integer min;

    /**
     * Maximum threshold
     */
    @Column(name = "max")
    private Integer max;

    /**
     * Value
     */
    @Column(name = "value")
    private Integer value;
}
