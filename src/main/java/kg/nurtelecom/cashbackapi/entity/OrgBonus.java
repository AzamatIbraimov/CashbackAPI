package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Organization Bonus
 * Bonuses created by organization admin for their clients
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "org_bonus")
public class OrgBonus {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "org_bonus_seq", sequenceName = "org_bonus_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_bonus_seq")
    private Long id;

    /**
     * Organization bonus type
     * Examples: Welcome Bonus, Fixed Bonus, Cash-back Bonus
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "org_bonus_type_id", referencedColumnName = "id")
    private OrgBonusType orgBonusType;

    /**
     * Organization
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Bonus start date
     */
    @DateTimeFormat(pattern="dd-MM-yyyy")
    @Column(name = "valid_from")
    private Date validFrom;

    /**
     * Bonus finish date
     */
    @DateTimeFormat(pattern="dd-MM-yyyy")
    @Column(name = "valid_to")
    private Date validTo;

    /**
     * Bonus validation in days till it's end
     */
    @Column(name = "validity")
    private Integer validity;

    /**
     * Bonus status
     * active = true / false
     */
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    /**
     * Created date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date createdDate;


    /**
     * Deleted date
     */
    @Column(name = "deleted_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date deletedDate;
}
